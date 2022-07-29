package org.liu.order.feign.pojo.resp;

import lombok.Data;
import org.liu.order.feign.pojo.po.Order;
import org.liu.order.feign.pojo.po.OrderItem;

import java.util.List;

@Data
public class OrderListResp extends Order {
    private List<OrderItem> orderItems;
}
