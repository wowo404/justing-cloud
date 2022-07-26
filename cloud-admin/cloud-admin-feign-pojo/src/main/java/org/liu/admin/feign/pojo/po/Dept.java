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

@TableName("sys_dept")
@Accessors(chain = true)
@Data
public class Dept implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 部门名称
     */
    @TableField
    private String name;
    /**
     * 上级部门ID，顶级部门为0
     */
    @TableField
    private Long parentId;
    /**
     * 祖级列表
     */
    @TableField
    private String ancestors;
    /**
     * 排序
     */
    @TableField
    private Integer sortNumber;
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
     * 更新者
     */
    @TableField
    private String updateBy;
    /**
     * 更新时间
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

