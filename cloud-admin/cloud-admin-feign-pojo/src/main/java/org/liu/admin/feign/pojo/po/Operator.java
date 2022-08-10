package org.liu.admin.feign.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String username;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 操作员姓名
     */
    private String operatorName;
    /**
     * 所属部门，超级管理员无所属部门
     */
    private Long deptId;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 头像地址
     */
    private String avatarUrl;
    /**
     * 密码是否过期（0-否，1-是）
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date credentialsExpireTime;
    /**
     * 是否删除：0-存在；1-已删除
     */
    private Integer deleted;
    /**
     * 状态：0-正常；1-冻结
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 最后登录IP
     */
    private String lastLoginIp;
    /**
     * 最后登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastLoginTime;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    /**
     * 租户ID
     */
    private Long tenantId;
}

