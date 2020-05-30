package org.liu.order.feign.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderListResp {

    private Long orderId;
    private BigDecimal amount;//订单金额
    private Integer itemNumber;//订单品类数量，同一个东西买N个算一个品类
    private Long userId;
    private Date createTime;

}
