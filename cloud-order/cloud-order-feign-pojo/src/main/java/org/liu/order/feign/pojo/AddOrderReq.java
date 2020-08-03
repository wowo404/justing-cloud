package org.liu.order.feign.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddOrderReq {
    private Long userId;
    private GoodsDetails details;
    private BigDecimal totalAmount;

    @Data
    public static class GoodsDetails {
        private Long goodsId;
        private BigDecimal unitPrice;
        private Integer count;
        private BigDecimal totalPrice;
    }
}
