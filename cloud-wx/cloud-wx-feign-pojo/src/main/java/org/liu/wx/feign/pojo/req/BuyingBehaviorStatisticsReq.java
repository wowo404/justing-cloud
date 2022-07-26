package org.liu.wx.feign.pojo.req;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
public class BuyingBehaviorStatisticsReq {
    private Long userId;
    private Long orderId;
    private BigDecimal totalAmount;
    private Map<Integer, List<BuyingCount>> details;//key是商品类别

    @Data
    public static class BuyingCount {
        private Long goodsId;//商品ID
        private Integer count;//购买数量
    }
}
