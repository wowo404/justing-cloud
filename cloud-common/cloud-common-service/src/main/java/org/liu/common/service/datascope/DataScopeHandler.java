package org.liu.common.service.datascope;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.handler.DataPermissionHandler;
import net.sf.jsqlparser.expression.Expression;
import org.liu.common.service.util.ServletUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.support.SecurityWebApplicationContextUtils;

import java.lang.reflect.Method;

/**
 * @Author lzs
 * @Date 2022/7/27 14:00
 **/
public class DataScopeHandler implements DataPermissionHandler {
    @Override
    public Expression getSqlSegment(Expression where, String mappedStatementId) {
        try {
            Class<?> clazz = Class.forName(mappedStatementId.substring(0, mappedStatementId.lastIndexOf(".")));
            String methodName = mappedStatementId.substring(mappedStatementId.lastIndexOf(".") + 1);
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                DataScope annotation = method.getAnnotation(DataScope.class);
                if (ObjectUtils.isNotEmpty(annotation) && (method.getName().equals(methodName) || (method.getName() + "_COUNT").equals(methodName))) {
                    // 获取当前的用户
                    LoginUser loginUser = SpringUtils.getBean(TokenService.class).getLoginUser(ServletUtils.getRequest());
                    if (ObjectUtils.isNotEmpty(loginUser) && ObjectUtils.isNotEmpty(loginUser.getUser()) && !loginUser.getUser().isAdmin()) {
                        return dataScopeFilter(loginUser.getUser(), annotation.value(), where);
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return where;
    }
}
