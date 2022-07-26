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

@TableName("sys_post")
@Accessors(chain = true)
@Data
public class Post implements Serializable {
    /**
     * 岗位ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 岗位编码
     */
    @TableField
    private String code;
    /**
     * 岗位名称
     */
    @TableField
    private String name;
    /**
     * 显示顺序
     */
    @TableField
    private Integer sortNumber;
    /**
     * 状态（0-正常,1-停用）
     */
    @TableField
    private Integer status;
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

