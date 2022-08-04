package org.liu.common.codegenerator;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by emmet on 17-3-29.
 * desc:
 */
public class Generate {

    /**
     * @param host        数据库IP
     * @param port        数据库端口号
     * @param database    数据库名
     * @param username    数据库用户名
     * @param password    数据库密码
     * @param tableName   表名
     * @param entityName  实体类名
     * @param basePackage 实体类包
     * @param entityPath  实体类路径
     * @throws Exception
     */
    public static void generate(String host, String port, String database, String username, String password,
                                String tableName, String entityName, String basePackage,
                                String entityPath, String mapperPath, String xmlPath, String servicePath, String serviceInterfacePath,
                                String controllerPath, String entitySuffix, String requestMappingPrefix) throws Exception {
        String entityPackage = basePackage + ".feign.pojo.po";
        String mapperPackage = basePackage + ".mapper";
        String servicePackage = basePackage + ".service.impl";
        String serviceInterfacePackage = basePackage + ".service";
        String controllerPackage = basePackage + ".controller";

        String mapperInterfaceName = entityName + "Mapper";//mapper接口的类名
        String xmlFileName = entityName + "Mapper";//xml文件名
        String serviceInterfaceName = entityName + "Service";//service接口类名
        String serviceClassName = entityName + "ServiceImpl";//service实现类类名
        String controllerClassName = entityName + "Controller";//controller类名
        //读取表结构
        TableInfo tableInfo = initTable(host, port, database, username, password, tableName);
        //生成实体类--会覆盖已存在文件
        GenerateEntity.generate(entityName + entitySuffix, entityPackage, entityPath, tableInfo);
        ///生成Mapper--不会覆盖
        GenerateMapper.generate(entityName, entityPackage, mapperPackage, mapperPath, entitySuffix, mapperInterfaceName);
        //生成xml--不会覆盖
        GenerateXml.generate(entityName, mapperPackage, xmlPath, mapperInterfaceName, xmlFileName);
        //生成Service接口--不会覆盖
        GenerateServiceInterface.generate(entityName, entityPackage, mapperPackage, serviceInterfacePackage,
                serviceInterfacePath, entitySuffix, serviceInterfaceName);
        //生成Service impl--不会覆盖
        GenerateService.generate(entityName, entityPackage, mapperPackage, servicePackage, servicePath,
                serviceInterfacePackage, entitySuffix, serviceClassName, serviceInterfaceName, mapperInterfaceName);
        //生成Controller--不会覆盖
        GenerateController.generate(entityName, tableInfo, entityPackage, mapperPackage, serviceInterfacePackage,
                servicePath, controllerPackage,
                controllerPath, entitySuffix, requestMappingPrefix, controllerClassName, serviceInterfaceName);
    }


    public static void main(String[] args) throws Exception {
        String basePath = "";
        String property = System.getProperty("user.name");
        if ("liuzhsh".equals(property)) {
            basePath = "D:/work/workspace-idea/justing-cloud/";
        } else if ("Administrator".equals(property)) {
            basePath = "E:/work/my/code/justing-cloud/";
        } else {
            return;
        }
//		cloud_activity(basePath);
//        cloud_admin(basePath);
//		cloud_coupon(basePath);
//		cloud_order(basePath);
//		cloud_product(basePath);
//		cloud_public(basePath);
//		cloud_storage(basePath);
        cloud_wx(basePath);
    }

    private static void cloud_admin(String basePath) throws Exception {
        String basePackage = "org.liu.admin";
        String entitySuffix = "";//po类的后缀，比如：XXXEntity
        String requestMappingPrefix = "";//requestMapping的前缀，比如：/api/**
        String microServicePath = basePath + "cloud-admin/cloud-admin-service/";
        String feignPojoPath = basePath + "cloud-admin/cloud-admin-feign-pojo/";
        String entityPath = feignPojoPath + "src/main/java/org/liu/admin/feign/pojo/po/";
        String mapperPath = microServicePath + "src/main/java/org/liu/admin/mapper/";
        String xmlPath = microServicePath + "src/main/resources/mapper/";
        String servicePath = microServicePath + "src/main/java/org/liu/admin/service/impl/";
        String serviceInterfacePath = microServicePath + "src/main/java/org/liu/admin/service/";
        String controllerPath = microServicePath + "src/main/java/org/liu/admin/controller/";
        Map<String, String> names = new HashMap<>();
        names.put("sys_config", "Config");
        names.put("sys_dept", "Dept");
        names.put("sys_dictionary", "Dictionary");
        names.put("sys_menu", "Menu");
        names.put("sys_operate_log", "OperateLog");
        names.put("sys_operator", "Operator");
        names.put("sys_operator_post", "OperatorPost");
        names.put("sys_operator_role", "OperatorRole");
        names.put("sys_post", "Post");
        names.put("sys_role", "Role");
        names.put("sys_role_menu", "RoleMenu");
        names.put("sys_tenant", "Tenant");
        for (Map.Entry<String, String> entry : names.entrySet()) {
            generate("81.71.18.47", "3306", "cloud_admin", "root", "20211027@nankang",
                    entry.getKey(), entry.getValue(), basePackage, entityPath, mapperPath, xmlPath, servicePath, serviceInterfacePath,
                    controllerPath, entitySuffix, requestMappingPrefix);
        }
    }

    private static void cloud_activity(String basePath) {

    }

    private static void cloud_coupon(String basePath) throws Exception {

    }

    private static void cloud_order(String basePath) throws Exception {
        String basePackage = "org.liu.order";
        String entitySuffix = "";//po类的后缀，比如：XXXEntity
        String requestMappingPrefix = "";//requestMapping的前缀，比如：/api/**
        String microServicePath = basePath + "cloud-order/cloud-order-service/";
        String feignPojoPath = basePath + "cloud-order/cloud-order-feign-pojo/";
        String entityPath = feignPojoPath + "src/main/java/org/liu/order/feign/pojo/po/";
        String mapperPath = microServicePath + "src/main/java/org/liu/order/mapper/";
        String xmlPath = microServicePath + "src/main/resources/mapper/";
        String servicePath = microServicePath + "src/main/java/org/liu/order/service/impl/";
        String serviceInterfacePath = microServicePath + "src/main/java/org/liu/order/service/";
        String controllerPath = microServicePath + "src/main/java/org/liu/order/controller/";
        Map<String, String> names = new HashMap<>();
        names.put("order", "Order");
        names.put("order_item", "OrderItem");
        for (Map.Entry<String, String> entry : names.entrySet()) {
            generate("81.71.18.47", "3306", "cloud_order", "root", "20211027@nankang",
                    entry.getKey(), entry.getValue(), basePackage, entityPath, mapperPath, xmlPath, servicePath, serviceInterfacePath,
                    controllerPath, entitySuffix, requestMappingPrefix);
        }
    }

    private static void cloud_product(String basePath) throws Exception {
        String basePackage = "org.liu.product";
        String entitySuffix = "";//po类的后缀，比如：XXXEntity
        String requestMappingPrefix = "";//requestMapping的前缀，比如：/api/**
        String microServicePath = basePath + "cloud-product/cloud-product-service/";
        String feignPojoPath = basePath + "cloud-product/cloud-product-feign-pojo/";
        String entityPath = feignPojoPath + "src/main/java/org/liu/product/feign/pojo/po/";
        String mapperPath = microServicePath + "src/main/java/org/liu/product/mapper/";
        String xmlPath = microServicePath + "src/main/resources/mapper/";
        String servicePath = microServicePath + "src/main/java/org/liu/product/service/impl/";
        String serviceInterfacePath = microServicePath + "src/main/java/org/liu/product/service/";
        String controllerPath = microServicePath + "src/main/java/org/liu/product/controller/";
        Map<String, String> names = new HashMap<>();
        names.put("brand", "Brand");
        names.put("category", "Category");
        names.put("category_brand", "CategoryBrand");
        names.put("sku", "Sku");
        names.put("spec_group", "SpecGroup");
        names.put("spec_param", "SpecParam");
        names.put("spu", "Spu");
        names.put("spu_detail", "SpuDetail");
        names.put("stock", "Stock");
        for (Map.Entry<String, String> entry : names.entrySet()) {
            generate("81.71.18.47", "3306", "cloud_product", "root", "20211027@nankang",
                    entry.getKey(), entry.getValue(), basePackage, entityPath, mapperPath, xmlPath, servicePath, serviceInterfacePath,
                    controllerPath, entitySuffix, requestMappingPrefix);
        }
    }

    private static void cloud_public(String basePath) {

    }

    private static void cloud_storage(String basePath) {

    }

    private static void cloud_wx(String basePath) throws Exception {
        String basePackage = "org.liu.wx";
        String entitySuffix = "";//po类的后缀，比如：XXXEntity
        String requestMappingPrefix = "";//requestMapping的前缀，比如：/api/**
        String microServicePath = basePath + "cloud-wx/cloud-wx-service/";
        String feignPojoPath = basePath + "cloud-wx/cloud-wx-feign-pojo/";
        String entityPath = feignPojoPath + "src/main/java/org/liu/wx/feign/pojo/po/";
        String mapperPath = microServicePath + "src/main/java/org/liu/wx/mapper/";
        String xmlPath = microServicePath + "src/main/resources/mapper/";
        String servicePath = microServicePath + "src/main/java/org/liu/wx/service/impl/";
        String serviceInterfacePath = microServicePath + "src/main/java/org/liu/wx/service/";
        String controllerPath = microServicePath + "src/main/java/org/liu/wx/controller/";
        Map<String, String> names = new HashMap<>();
        names.put("msg_template", "MsgTemplate");
        names.put("msg_template_param", "MsgTemplateParam");
        names.put("wx_app", "WxApp");
        names.put("wx_auto_reply", "WxAutoReply");
        names.put("wx_mass_msg", "WxMassMsg");
        names.put("wx_mp_menu", "WxMpMenu");
        names.put("wx_msg", "WxMsg");
        names.put("wx_user", "WxUser");
        for (Map.Entry<String, String> entry : names.entrySet()) {
            generate("81.71.18.47", "3306", "cloud_wx", "root", "20211027@nankang",
                    entry.getKey(), entry.getValue(), basePackage, entityPath, mapperPath, xmlPath, servicePath, serviceInterfacePath,
                    controllerPath, entitySuffix, requestMappingPrefix);
        }
    }

    private static TableInfo initTable(String host, String port, String database, String username, String password, String tableName) throws Exception {
        TableInfo tableInfo = new TableInfo();
        tableInfo.setTableName(tableName);
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = String.format("jdbc:mysql://%s:%s/%s?serverTimezone=UTC&useSSL=false", host, port, database);
        Connection conn = DriverManager.getConnection(url, username, password);
        String tableSql = "select TABLE_COMMENT from information_schema.tables where table_name = '" + tableName + "'";
        PreparedStatement preparedStatement = conn.prepareStatement(tableSql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            tableInfo.setTableComment(resultSet.getString(1));
        }
        String strsql = "SELECT COLUMN_NAME, DATA_TYPE, COLUMN_KEY, COLUMN_COMMENT, EXTRA  FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = '" + database + "' AND TABLE_NAME = '" + tableName + "'" + " ORDER BY ORDINAL_POSITION"; //读一行记录;
        PreparedStatement pstmt = conn.prepareStatement(strsql);
        ResultSet result = pstmt.executeQuery();
        while (result.next()) {
            if (GenerateEntity.ignore_columns.contains(result.getString(1))) {
                continue;
            }
            tableInfo.getColnames().add(result.getString(1));
            tableInfo.getColTypes().add(result.getString(2));
            tableInfo.getColComment().add(result.getString(4));
            tableInfo.getExtra().add(result.getString(5));
            if ("date".equals(result.getString(2)) || "datetime".equals(result.getString(2)) || "timestamp".equals(result.getString(2))) {
                tableInfo.setImportDate(true);
            }
            if ("decimal".equals(result.getString(2))) tableInfo.setImportBigDecimal(true);
        }
        if (conn != null) conn.close();
        return tableInfo;
    }

    @Getter
    @Setter
    public static class TableInfo {
        private String tableName;
        private String tableComment;
        private List<String> colnames = new ArrayList<>();
        private List<String> colTypes = new ArrayList<>();
        private List<String> colComment = new ArrayList<>();
        private List<String> extra = new ArrayList<>();
        private Boolean importDate = false;
        private Boolean importDateTime = false;
        private Boolean importBigDecimal = false;
    }

    @Data
    public static class GenerateObjectInfo {
        private String tableName;//表名
        private String entityName;//PO的名字
        private String mapperInterfaceName;
        private String xmlFileName;
        private String serviceInterfaceName;
        private String serviceClassName;
        private String controllerClassName;
    }

}
