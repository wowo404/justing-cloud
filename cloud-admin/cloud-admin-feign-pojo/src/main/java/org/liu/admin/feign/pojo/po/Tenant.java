package org.liu.admin.feign.pojo.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@TableName("sys_tenant")
@Accessors(chain = true)
@Data
public class Tenant implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 名称
     */
    @TableField
    private String name;
    /**
     * 租户管理登录账号
     */
    @TableField
    private String account;
    /**
     * 密码
     */
    @TableField
    private String password;
    /**
     * 手机
     */
    @TableField
    private String phone;
    /**
     * 邮箱
     */
    @TableField
    private String email;
    /**
     * 地址
     */
    @TableField
    private String address;
    /**
     * 角色ID
     */
    @TableField
    private Long roleId;
    /**
     * 到期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField
    private Date expirationDate;
    /**
     * 是否启用（0-是，1-否）
     */
    @TableField
    private Integer enable;
    /**
     * 是否删除：0-存在；1-已删除
     */
    @TableField
    private Integer deleted;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField
    private Date createTime;
    /**
     * 创建者
     */
    @TableField
    private String createBy;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField
    private Date updateTime;
    /**
     * 更新者
     */
    @TableField
    private String updateBy;
}

