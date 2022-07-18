package org.liu.common.codegenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by emmet on 17-3-29.
 * desc:
 */
public class GenerateController {

    //生成Service类
    public static void generate(String entityName, Generate.TableInfo tableInfo, String entityPackage, String mapperPackage, String serviceInterfacePackage, String servicePath,
                                String controllerPackage, String controllerPath, String entitySuffix, String requestMappingPrefix,
                                String controllerClassName, String serviceInterfaceName) throws IOException {
        File file = new File(controllerPath + controllerClassName + ".java");
        if (file.exists()) return;

        StringBuffer sb = new StringBuffer();
        sb.append("package ").append(controllerPackage).append(";\r\n").append("\r\n");

        sb.append("import com.baomidou.mybatisplus.extension.plugins.pagination.Page;\r\n");
        sb.append("import lombok.RequiredArgsConstructor;\r\n");
        sb.append("import lombok.extern.slf4j.Slf4j;\r\n");
        sb.append("import org.justing.commons.model.Response;\r\n");
        sb.append("import ").append(entityPackage).append(".").append(entityName + entitySuffix).append(";\r\n");
        sb.append("import ").append(serviceInterfacePackage).append(".").append(serviceInterfaceName).append(";\r\n");
        sb.append("import org.springframework.web.bind.annotation.*;\r\n");
        sb.append("\r\n");

        sb.append("/**\r\n");
        sb.append(" * " + tableInfo.getTableComment() + "\r\n");
        sb.append(" */\r\n");
        sb.append("@Slf4j\r\n");
		sb.append("@RequiredArgsConstructor\r\n");
        sb.append("@RestController\r\n");
        sb.append("@RequestMapping(\"" + requestMappingPrefix + "/" + initial(entityName) + "\")\r\n");
        sb.append("public class ").append(controllerClassName).append(" {\r\n");
        sb.append("\r\n");
        sb.append("    private final " + serviceInterfaceName + " " + initial(serviceInterfaceName) + ";\r\n");
        sb.append("\r\n");
        //--------分页列表
        sb.append("    /**\r\n");
        sb.append("     * 分页列表\r\n");
        sb.append("     */\r\n");
        sb.append("    @PostMapping(\"pageList\")\r\n");
        sb.append("    public Response<Page<Long>> pageList() {\r\n");
        sb.append("        return Response.ok(" + initial(serviceInterfaceName) + ".pageList());\r\n");
        sb.append("    }\r\n");
        sb.append("\r\n");
        //--------
        //--------详情
        sb.append("    /**\r\n");
        sb.append("     * 详情\r\n");
        sb.append("     */\r\n");
        sb.append("    @GetMapping(\"/{id}\")\r\n");
        sb.append("    public Response<Long> detail(@PathVariable(\"id\") Long id) {\r\n");
        sb.append("        return Response.ok(" + initial(serviceInterfaceName) + ".detail(id));\r\n");
        sb.append("    }\r\n");
        sb.append("\r\n");
        //--------
        //--------新增
        sb.append("    /**\r\n");
        sb.append("     * 新增\r\n");
        sb.append("     */\r\n");
        sb.append("    @PostMapping(\"add\")\r\n");
        sb.append("    public Response<Long> add() {\r\n");
        sb.append("        return Response.ok(" + initial(serviceInterfaceName) + ".add());\r\n");
        sb.append("    }\r\n");
        sb.append("\r\n");
        //--------
        //--------编辑
        sb.append("    /**\r\n");
        sb.append("     * 编辑\r\n");
        sb.append("     */\r\n");
        sb.append("    @PutMapping(\"edit\")\r\n");
        sb.append("    public Response<Void> edit() {\r\n");
        sb.append("        " + initial(serviceInterfaceName) + ".edit();\r\n");
        sb.append("        return Response.ok();\r\n");
        sb.append("    }\r\n");
        sb.append("\r\n");
        //--------
        //--------删除
        sb.append("    /**\r\n");
        sb.append("     * 删除\r\n");
        sb.append("     */\r\n");
        sb.append("    @DeleteMapping(\"/{ids}\")\r\n");
        sb.append("    public Response<Void> delete(@PathVariable Long[] ids) {\r\n");
        sb.append("        " + initial(serviceInterfaceName) + ".delete(ids);\r\n");
        sb.append("        return Response.ok();\r\n");
        sb.append("    }\r\n");
        //----------
        sb.append("}\r\n");
        String content = sb.toString();
        System.out.println(content);
        FileWriter fw = new FileWriter(controllerPath + controllerClassName + ".java");
        PrintWriter pw = new PrintWriter(fw);
        pw.println(content);
        pw.flush();
        pw.close();
    }

    //把输入字符串的首字母改成小写
    private static String initial(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'A' && ch[0] <= 'Z') ch[0] = (char) (ch[0] + 32);
        return new String(ch);
    }

}
