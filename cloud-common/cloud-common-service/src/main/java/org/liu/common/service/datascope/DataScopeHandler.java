package org.liu.common.service.datascope;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.db.dialect.impl.MysqlDialect;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.handler.DataPermissionHandler;
import lombok.RequiredArgsConstructor;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SubSelect;
import org.justing.commons.exception.CommonException;
import org.liu.common.core.enums.DataScopeEnum;
import org.liu.common.security.base.util.SecurityUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author lzs
 * @Date 2022/7/27 14:00
 **/
@Component
@RequiredArgsConstructor
public class DataScopeHandler implements DataPermissionHandler {

    private final DataSource dataSource;
    private static String DEFAULT_COLUMN_NAME_DEPT_ID = "dept_id";
    private static String DEFAULT_COLUMN_NAME_CREATE_ID = "create_id";
    private static String SCHEME_ADMIN = "cloud_admin";
    private static String sql = "select id from cloud_admin.sys_dept where find_in_set({}, ancestors)";

    @Override
    public Expression getSqlSegment(Expression where, String mappedStatementId) {
        try {
            String methodName = mappedStatementId.substring(mappedStatementId.lastIndexOf(".") + 1);
            Class<?> clazz = Class.forName(mappedStatementId.substring(0, mappedStatementId.lastIndexOf(".")));
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                DataScope annotation = method.getAnnotation(DataScope.class);
                if (ObjectUtils.isNotEmpty(annotation) && method.getName().equals(methodName)) {
                    // 未登录、超级租户、超级管理员角色、超级管理员操作员都不作处理
                    if (null == SecurityUtils.getBaseUser() || SecurityUtils.isSuper()) {
                        return where;
                    }
                    return doGetSqlSegment(annotation, where);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new CommonException(e);
        }
        return where;
    }

    /**
     * 构建过滤条件
     *
     * @param where 当前查询条件
     * @return 构建后查询条件
     */
    private Expression doGetSqlSegment(DataScope annotation, Expression where) throws SQLException {
        DataScopeEnum dataScope = SecurityUtils.getBaseUser().getDataScope();
        List<Long> deptIds = SecurityUtils.getDeptIds();//部门ID列表
        String tableAlias = annotation.alias();
        String deptIdColumnName = StrUtil.isBlank(annotation.columnName()) ? DEFAULT_COLUMN_NAME_DEPT_ID : annotation.columnName();
        String createIdColumnName = StrUtil.isBlank(annotation.columnName()) ? DEFAULT_COLUMN_NAME_CREATE_ID : annotation.columnName();
        Expression expression = null;
        if (DataScopeEnum.ALL.equals(dataScope)) {
            return where;
        }
        if (DataScopeEnum.DEPT_AND_CHILD.equals(dataScope)) {
            InExpression inExpression = new InExpression();
            //如果是cloud_admin微服务，内部关联查询即可
            String schema = dataSource.getConnection().getSchema();
            if (schema.equalsIgnoreCase(SCHEME_ADMIN)) {
                //{alias}.dept_id in (select dept_id from sys_dept where dept_id = {deptId} or find_in_set({deptId}, ancestors))
                //定义一个查询sql：select dept_id from sys_dept where dept_id = {deptId} or find_in_set({deptId}, ancestors)
                PlainSelect select = new PlainSelect();
                select.setSelectItems(Collections.singletonList(new SelectExpressionItem(new Column(deptIdColumnName))));
                select.setFromItem(new Table("sys_dept"));
                //定义子查询的第一个条件：dept_id = {deptId}
                EqualsTo equalsTo = new EqualsTo();
                equalsTo.setLeftExpression(new Column(deptIdColumnName));
                equalsTo.setRightExpression(new LongValue(deptIds.get(0)));
                //定义子查询的第二个条件：find_in_set({deptId}, ancestors)
                Function function = new Function();
                function.setName("find_in_set");
                function.setParameters(new ExpressionList(new LongValue(deptIds.get(0)), new Column("ancestors")));
                //子查询的两个条件用or连接
                select.setWhere(new OrExpression(equalsTo, function));
                //把上面定义的查询sql当作一个子查询
                SubSelect subSelect = new SubSelect();
                subSelect.setSelectBody(select);
                //in的右侧
                inExpression.setRightExpression(subSelect);
            } else {
                //{alias}.dept_id in {deptIds}
                List<Entity> list = Db.use(dataSource, new MysqlDialect()).query(StrUtil.format(sql, deptIds.get(0)));
                List<Long> ids = list.stream().map(entity -> entity.getLong("id")).collect(Collectors.toList());
                if (CollUtil.isNotEmpty(ids)) {
                    deptIds.addAll(ids);
                }
                //in的右侧
                ItemsList itemsList = new ExpressionList(deptIds.stream().map(LongValue::new).collect(Collectors.toList()));
                inExpression.setRightItemsList(itemsList);
            }
            //in的左侧
            inExpression.setLeftExpression(buildColumn(tableAlias, deptIdColumnName));
            expression = inExpression;
        } else if (DataScopeEnum.DEPT.equals(dataScope)) {
            //{alias}.dept_id = {deptId}
            EqualsTo equalsTo = new EqualsTo();
            equalsTo.setLeftExpression(buildColumn(tableAlias, deptIdColumnName));
            equalsTo.setRightExpression(new LongValue(deptIds.get(0)));
            expression = equalsTo;
        } else if (DataScopeEnum.SELF.equals(dataScope)) {
            //{alias}.create_id = {id}
            EqualsTo equalsTo = new EqualsTo();
            equalsTo.setLeftExpression(buildColumn(tableAlias, createIdColumnName));
            equalsTo.setRightExpression(new LongValue(SecurityUtils.getId()));
            expression = equalsTo;
        } else if (DataScopeEnum.CUSTOM.equals(dataScope)) {
            //{alias}.dept_id in {deptIds}
            ItemsList itemsList = new ExpressionList(deptIds.stream().map(LongValue::new).collect(Collectors.toList()));
            InExpression inExpression = new InExpression();
            inExpression.setLeftExpression(buildColumn(tableAlias, deptIdColumnName));
            inExpression.setRightItemsList(itemsList);
            expression = inExpression;
        }
        return ObjectUtils.isNotEmpty(where) ? new AndExpression(where, new Parenthesis(expression)) : expression;
    }

    /**
     * 构建Column
     *
     * @param tableAlias 表别名
     * @param columnName 字段名称
     * @return 带表别名字段
     */
    private Column buildColumn(String tableAlias, String columnName) {
        return new Column(tableAlias + "." + columnName);
    }
}
