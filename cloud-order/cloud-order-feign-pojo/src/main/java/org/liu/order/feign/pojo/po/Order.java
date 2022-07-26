package org.liu.order.feign.pojo.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@TableName("order")
@Accessors(chain = true)
@Data
public class Order implements Serializable {
    /**
     * PK
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 订单编号
     */
    @TableField
    private String code;
    /**
     * 微信用户id
     */
    @TableField
    private Long wxUserId;
    /**
     * 订单类型（0、普通订单；1、砍价订单）
     */
    @TableField
    private Integer orderType;
    /**
     * 支付场景（0、在线支付；1、线下支付）
     */
    @TableField
    private Integer paymentWay;
    /**
     * 支付类型（0、微信支付；1、支付宝支付；2、云闪付）
     */
    @TableField
    private Integer paymentType;
    /**
     * 支付状态（0、未支付；1、支付中；2、支付成功；3、支付失败）
     */
    @TableField
    private Integer payStatus;
    /**
     * 订单状态（0、待发货；1、待收货；2、确认收货/已完成；3、已关闭）
     */
    @TableField
    private Integer status;
    /**
     * 评价状态（0、未评；1、已评；2、已追评）
     */
    @TableField
    private Integer appraiseStatus;
    /**
     * 运费金额
     */
    @TableField
    private BigDecimal freightPrice;
    /**
     * 销售金额
     */
    @TableField
    private BigDecimal salesPrice;
    /**
     * 积分抵扣金额
     */
    @TableField
    private BigDecimal paymentPointsPrice;
    /**
     * 优惠券抵扣金额
     */
    @TableField
    private BigDecimal paymentCouponPrice;
    /**
     * 支付金额（销售金额+运费金额-积分抵扣金额-优惠券抵扣金额）
     */
    @TableField
    private BigDecimal paymentPrice;
    /**
     * 支付积分
     */
    @TableField
    private Integer paymentPoints;
    /**
     * 总件数
     */
    @TableField
    private Integer totalNum;
    /**
     * 清单数量
     */
    @TableField
    private Integer itemNum;
    /**
     * 付款时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField
    private Date paymentTime;
    /**
     * 发货时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField
    private Date deliveryTime;
    /**
     * 收货时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField
    private Date receiverTime;
    /**
     * 成交时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField
    private Date closingTime;
    /**
     * 买家留言
     */
    @TableField
    private String buyerMessage;
    /**
     * 物流id
     */
    @TableField
    private Long logisticsId;
    /**
     * 微信支付交易ID
     */
    @TableField
    private String transactionId;
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
     * 租户ID
     */
    @TableField
    private Long tenantId;
}

