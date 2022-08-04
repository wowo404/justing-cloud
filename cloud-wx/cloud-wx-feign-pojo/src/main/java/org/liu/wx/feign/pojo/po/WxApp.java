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

@TableName("wx_app")
@Accessors(chain = true)
@Data
public class WxApp implements Serializable {
    /**
     * 微信appId，也是主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String appId;
    /**
     * 微信原始标识
     */
    @TableField
    private String weixinSign;
    /**
     * 微信号名称
     */
    @TableField
    private String name;
    /**
     * 应用类型(1-小程序；2-公众号；3-app)
     */
    @TableField
    private Integer appType;
    /**
     * 应用密钥
     */
    @TableField
    private String secret;
    /**
     * token
     */
    @TableField
    private String token;
    /**
     * EncodingAESKey
     */
    @TableField
    private String aesKey;
    /**
     * 是否第三方平台应用（0：否；1：是）
     */
    @TableField
    private Integer isComponent;
    /**
     * 0：订阅号；1：由历史老帐号升级后的订阅号；2：服务号
     */
    @TableField
    private Integer weixinType;
    /**
     * 公众号微信号
     */
    @TableField
    private String weixinHao;
    /**
     * 认证类型
     */
    @TableField
    private Integer verifyType;
    /**
     * logo
     */
    @TableField
    private String logo;
    /**
     * 二维码
     */
    @TableField
    private String qrCode;
    /**
     * 主体名称
     */
    @TableField
    private String principalName;
    /**
     * 微信支付商户号
     */
    @TableField
    private String mchId;
    /**
     * 微信支付商户密钥
     */
    @TableField
    private String mchKey;
    /**
     * p12证书的位置，可以指定绝对路径，也可以指定类路径（以classpath:开头）
     */
    @TableField
    private String keyPath;
    /**
     * 备注信息
     */
    @TableField
    private String remarks;
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
     * 租户ID
     */
    @TableField
    private Long tenantId;
}

