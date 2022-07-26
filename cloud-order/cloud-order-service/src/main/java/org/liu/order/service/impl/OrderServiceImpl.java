package org.liu.order.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.justing.commons.exception.CommonException;
import org.liu.order.feign.pojo.enums.PayStatusEnum;
import org.liu.order.feign.pojo.enums.PaymentWayEnum;
import org.liu.order.feign.pojo.po.Order;
import org.liu.order.feign.pojo.req.AddOrderReq;
import org.liu.order.mapper.OrderMapper;
import org.liu.order.service.OrderService;
import org.liu.product.feign.client.StockClient;
import org.liu.wx.feign.client.UserClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.liu.order.feign.pojo.exception.BizCodeEnum.MISSING_PAYMENT_TYPE;
import static org.liu.order.feign.pojo.exception.BizCodeEnum.WRONG_PAYMENT_PRICE;

@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    private final UserClient userClient;
    private final StockClient stockClient;
    private static final Snowflake snowFlake = new Snowflake();

    @Override
    public Page<Long> pageList() {
        return null;
    }

    @Override
    public Long detail(Long id) {
        return null;
    }

    @Override
    public Long add(AddOrderReq req) {
        Order order = new Order();
        BeanUtil.copyProperties(req, order);
        check(order);

        order.setCode(snowFlake.nextIdStr());
        order.setPayStatus(PayStatusEnum.NOT_PAY.getCode());

        List<AddOrderReq.AddOrderItemReq> orderItems = req.getOrderItems();
        BigDecimal freightPrice = BigDecimal.ZERO;
        BigDecimal salesPrice = BigDecimal.ZERO;
        BigDecimal paymentPointsPrice = BigDecimal.ZERO;
        BigDecimal paymentCouponPrice = BigDecimal.ZERO;
        BigDecimal paymentPrice = BigDecimal.ZERO;
        Integer paymentPoints = 0;
        Integer totalNum = 0;
        for (AddOrderReq.AddOrderItemReq orderItem : orderItems) {
            freightPrice = freightPrice.add(orderItem.getFreightPrice());
            salesPrice = salesPrice.add(orderItem.getSalesPrice().multiply(new BigDecimal(orderItem.getQuantity())));
            paymentPointsPrice = paymentPointsPrice.add(orderItem.getPaymentPointsPrice());
            paymentCouponPrice = paymentCouponPrice.add(orderItem.getPaymentCouponPrice());
            paymentPrice = paymentPrice.add(orderItem.getPaymentPrice());//orderItem中的paymentPrice相加
            paymentPoints += orderItem.getPaymentPoints();
            totalNum += orderItem.getQuantity();
        }
        if (paymentPrice.compareTo(salesPrice.add(freightPrice).subtract(paymentPointsPrice).subtract(paymentCouponPrice)) != 0) {
            throw new CommonException(WRONG_PAYMENT_PRICE);
        }
        if (paymentPrice.compareTo(req.getPaymentPrice()) != 0) {
            throw new CommonException(WRONG_PAYMENT_PRICE);
        }
        order.setFreightPrice(freightPrice);
        order.setSalesPrice(salesPrice);
        order.setPaymentPointsPrice(paymentPointsPrice);
        order.setPaymentCouponPrice(paymentCouponPrice);
        order.setPaymentPrice(paymentPrice);
        order.setPaymentPoints(paymentPoints);
        order.setTotalNum(totalNum);
        order.setItemNum(orderItems.size());

        super.save(order);
        return null;
    }

    private void check(Order order) {
        if (PaymentWayEnum.ONLINE.getCode().equals(order.getPaymentWay()) && null == order.getPaymentType()) {
            throw new CommonException(MISSING_PAYMENT_TYPE);
        }

    }

    @Override
    public void edit() {
    }

    @Override
    public void delete(Long[] ids) {
    }
}

