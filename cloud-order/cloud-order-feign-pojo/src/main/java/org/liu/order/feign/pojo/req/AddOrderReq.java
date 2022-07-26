package org.liu.order.feign.pojo.req;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
public class AddOrderReq {
    /**
     * 订单类型（0、普通订单；1、砍价订单）
     */
    @NotNull
    private Integer orderType;
    /**
     * 支付场景（0、在线支付；1、线下支付）
     */
    @NotNull
    private Integer paymentWay;
    /**
     * 支付类型（0、微信支付；1、支付宝支付；2、云闪付）
     */
    private Integer paymentType;
    /**
     * 支付金额（销售金额+运费金额-积分抵扣金额-优惠券抵扣金额）
     */
    @NotNull
    private BigDecimal paymentPrice;
    /**
     * 买家留言
     */
    private String buyerMessage;
    /**
     * 物流id
     */
    @NotNull
    private Long logisticsId;
    /**
     * 订单清单
     */
    @NotEmpty
    private List<AddOrderItemReq> orderItems;

    @Valid
    @Data
    public static class AddOrderItemReq {
        /**
         * skuId
         */
        @NotNull
        private Long skuId;
        /**
         * 商品数量
         */
        @Range(min = 1, max = Integer.MAX_VALUE)
        @NotNull
        private Integer quantity;
        /**
         * 购买单价
         */
        @NotNull
        private BigDecimal salesPrice;
        /**
         * 运费金额
         */
        private BigDecimal freightPrice = BigDecimal.ZERO;
        /**
         * 积分抵扣金额
         */
        private BigDecimal paymentPointsPrice = BigDecimal.ZERO;
        /**
         * 优惠券抵扣金额
         */
        private BigDecimal paymentCouponPrice = BigDecimal.ZERO;
        /**
         * 支付金额（购买单价*商品数量+运费金额-积分抵扣金额-优惠券抵扣金额）
         */
        @NotNull
        private BigDecimal paymentPrice;
        /**
         * 支付积分
         */
        private Integer paymentPoints = 0;
        /**
         * 优惠券ID
         */
        private Long couponId;
        /**
         * 备注
         */
        private String remark;
    }

}
