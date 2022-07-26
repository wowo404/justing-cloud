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

@TableName("order_item")
@Accessors(chain = true)
@Data
public class OrderItem implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 订单ID
     */
    @TableField
    private Long orderId;
    /**
     * 商品ID
     */
    @TableField
    private Long spuId;
    /**
     * 商品名
     */
    @TableField
    private String spuName;
    /**
     * 规格信息
     */
    @TableField
    private String specInfo;
    /**
     * skuId
     */
    @TableField
    private Long skuId;
    /**
     * 图片
     */
    @TableField
    private String picUrl;
    /**
     * 商品数量
     */
    @TableField
    private Integer quantity;
    /**
     * 购买单价
     */
    @TableField
    private BigDecimal salesPrice;
    /**
     * 运费金额
     */
    @TableField
    private BigDecimal freightPrice;
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
     * 支付金额（购买单价*商品数量+运费金额-积分抵扣金额-优惠券抵扣金额）
     */
    @TableField
    private BigDecimal paymentPrice;
    /**
     * 支付积分
     */
    @TableField
    private Integer paymentPoints;
    /**
     * 优惠券ID
     */
    @TableField
    private Long couponId;
    /**
     * 备注
     */
    @TableField
    private String remark;
    /**
     * 状态：0-正常；1-退款中；2-退货退款中；3-完成退款；4-完成退货退款
     */
    @TableField
    private Integer status;
    /**
     * 是否退款：0-否；1-是
     */
    @TableField
    private Integer isRefund;
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

