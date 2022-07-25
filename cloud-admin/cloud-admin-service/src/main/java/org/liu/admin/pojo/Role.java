package org.liu.admin.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

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
     * 备注
     */
    @TableField
    private String remark;
    /**
     * 状态（0-正常，1-注销）
     */
    @TableField
    private Integer status;
    /**
     * 创建者
     */
    @TableField
    private String createBy;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField
    private Date createTime;
    /**
     * 修改者
     */
    @TableField
    private String updateBy;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField
    private Date updateTime;
    /**
     * 租户ID
     */
    @TableField
    private Long tenantId;
}

