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

@TableName("wx_user")
@Accessors(chain = true)
@Data
public class WxUser implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * wx_app.id
     */
    @TableField
    private Long appId;
    /**
     * 手机号码
     */
    @TableField
    private String phoneNumber;
    /**
     * 昵称
     */
    @TableField
    private String nickName;
    /**
     * 性别（0-未知，1-男性，2-女性）
     */
    @TableField
    private String gender;
    /**
     * 市
     */
    @TableField
    private String city;
    /**
     * 省
     */
    @TableField
    private String province;
    /**
     * 区
     */
    @TableField
    private String country;
    /**
     * 头像地址
     */
    @TableField
    private String avatarUrl;
    /**
     * 显示省市区所用的语言
     */
    @TableField
    private String language;
    /**
     * unionId
     */
    @TableField
    private String unionId;
    /**
     * openId
     */
    @TableField
    private String openId;
    /**
     * 状态（0-正常，1-冻结）
     */
    @TableField
    private Integer status;
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
    /**
     * 租户ID
     */
    @TableField
    private Long tenantId;
}

