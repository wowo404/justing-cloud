package org.liu.admin.feign.pojo.resp;

import lombok.Data;
import org.liu.admin.feign.pojo.po.Menu;
import org.liu.admin.feign.pojo.po.Operator;
import org.liu.admin.feign.pojo.po.Role;

import java.util.List;

@Data
public class OperatorDetailResp extends Operator {
    /**
     * 角色列表
     */
    private List<Role> roles;
    /**
     * 菜单列表
     */
    private List<Menu> menus;
}
