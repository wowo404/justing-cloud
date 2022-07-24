package org.liu.admin.feign.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@TableName("sys_role_menu")
@Accessors(chain = true)
@Data
public class RoleMenu implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 角色编号
     */
    @TableField
    private Long roleId;
    /**
     * 资源编号
     */
    @TableField
    private Long menuId;
}

