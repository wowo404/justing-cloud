package org.liu.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.liu.order.feign.pojo.po.Order;
import org.liu.order.feign.pojo.req.AddOrderReq;

public interface OrderService extends IService<Order> {
    Page<Long> pageList();

    Long detail(Long id);

    Long add(AddOrderReq req);

    void edit();

    void delete(Long[] ids);
}

