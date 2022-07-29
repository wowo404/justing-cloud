package org.liu.order.feign.pojo.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author lzs
 * @Date 2022/7/28 11:37
 **/
@Data
public class PayOrderReq {
    @NotNull
    private Long orderId;
}
