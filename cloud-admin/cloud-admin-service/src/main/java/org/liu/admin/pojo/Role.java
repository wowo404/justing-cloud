package org.liu.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@TableName("sys_role")
@Accessors(chain = true)
@Data
public class Role implements Serializable {
    /**
     * 角色ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 角色编码
     */
    @TableField
    private String code;
    /**
     * 角色名称
     */
    @TableField
    private String name;
    /**
     * 数据范围（0-全部数据权限，1-自定数据权限，2-本部门数据权限，3-本部门及以下数据权限，4-自定义数据权限）
     */
    @TableField
    private Integer dataScope;
    /**
     * 是否删除：0-存在；1-已删除
     */
    @TableField
    private Integer deleted;
    /**
     * 状态（0-正常，1-注销）
     */
    @TableField
    private Integer status;
    /**
     * 租户ID
     */
    @TableField
    private Long tenantId;
}

