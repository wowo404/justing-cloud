package org.liu.wx.feign.pojo.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@TableName("msg_template")
@Accessors(chain = true)
@Data
public class MsgTemplate implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 公众号配置ID、小程序AppID
     */
    @TableField
    private String appId;
    /**
     * 模板id
     */
    @TableField
    private String templateId;
    /**
     * 1一次性，2永久
     */
    @TableField
    private Integer type;
    /**
     * 跳转小程序类型：developer为开发版；trial为体验版；formal为正式版；默认为正式版
     */
    @TableField
    private String miniprogramState;
    /**
     * 标题
     */
    @TableField
    private String title;
    /**
     * 备注
     */
    @TableField
    private String remark;
    /**
     * 是否删除：0-存在；1-已删除
     */
    @TableField
    private Integer deleted;
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
     * 所属租户
     */
    @TableField
    private Long tenantId;
}

