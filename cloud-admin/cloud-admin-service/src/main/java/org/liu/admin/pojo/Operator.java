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

@TableName("sys_operator")
@Accessors(chain = true)
@Data
public class Operator implements Serializable {
    /**
     * 操作员ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 操作员账号
     */
    @TableField
    private String username;
    /**
     * 登录密码
     */
    @TableField
    private String password;
    /**
     * 操作员姓名
     */
    @TableField
    private String operatorName;
    /**
     * 所属部门，超级管理员无所属部门
     */
    @TableField
    private Long deptId;
    /**
     * 手机
     */
    @TableField
    private String mobile;
    /**
     * 邮箱
     */
    @TableField
    private String email;
    /**
     * 头像地址
     */
    @TableField
    private String avatarUrl;
    /**
     * 是否删除：0-存在；1-已删除
     */
    @TableField
    private Integer deleted;
    /**
     * 状态：0-正常；1-冻结；2-注销
     */
    @TableField
    private Integer status;
    /**
     * 最后登录IP
     */
    @TableField
    private String lastLoginIp;
    /**
     * 最后登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField
    private Date lastLoginTime;
    /**
     * 租户ID
     */
    @TableField
    private Long tenantId;
}

