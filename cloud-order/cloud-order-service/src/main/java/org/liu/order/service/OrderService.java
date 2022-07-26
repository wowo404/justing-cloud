package org.liu.order.service;

import lombok.RequiredArgsConstructor;
import org.justing.commons.util.SnowFlake;
import org.liu.order.feign.pojo.AddOrderReq;
import org.liu.order.pojo.Order;
import org.liu.product.feign.client.StockClient;
import org.liu.product.feign.pojo.req.EditStockReq;
import org.liu.user.feign.client.UserClient;
import org.liu.user.feign.pojo.BuyingBehaviorStatisticsReq;
import org.liu.user.feign.pojo.OperateAccountReq;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Transactional
@Service
public class OrderService {

    private final UserClient userClient;
    private final StockClient stockClient;
    private static final SnowFlake snowFlake = new SnowFlake(1L, 1L);

    public Order addOrder(AddOrderReq req) {
        Order order = new Order();
        order.setOrderId(snowFlake.nextId());

        //执行扣减库存操作
        EditStockReq stockReq = new EditStockReq();
        stockReq.setSkuId(1L);
        stockReq.setStock(1);
        stockClient.edit(stockReq);
        //执行保存订单操作
        //执行用户账户扣减操作
        OperateAccountReq operateAccountReq = new OperateAccountReq();
        operateAccountReq.setUserId(req.getUserId());
        operateAccountReq.setAmount(order.getAmount());
        userClient.operateAccount(operateAccountReq);

        //增加用户购买行为统计
        //商品品类ID=1
        BuyingBehaviorStatisticsReq.BuyingCount buyingCount = new BuyingBehaviorStatisticsReq.BuyingCount();
        buyingCount.setGoodsId(1L);
        buyingCount.setCount(10);

        List<BuyingBehaviorStatisticsReq.BuyingCount> list = new ArrayList<>();
        list.add(buyingCount);

        Map<Integer, List<BuyingBehaviorStatisticsReq.BuyingCount>> details = new HashMap<>();
        details.put(1, list);

        BuyingBehaviorStatisticsReq statisticsReq = new BuyingBehaviorStatisticsReq();
        statisticsReq.setOrderId(order.getOrderId());
        statisticsReq.setUserId(req.getUserId());
        statisticsReq.setDetails(details);
        userClient.addBuyingBehaviorStatistics(statisticsReq);
        return order;
    }
}
