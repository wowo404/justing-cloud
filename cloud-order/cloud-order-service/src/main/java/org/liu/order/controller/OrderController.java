package org.liu.order.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.justing.commons.model.Response;
import org.liu.order.feign.pojo.po.Order;
import org.liu.order.feign.pojo.req.AddOrderReq;
import org.liu.order.service.OrderService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 订单
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    /**
     * 分页列表
     */
    @PostMapping("pageList")
    public Response<Page<Long>> pageList() {
        return Response.ok(orderService.pageList());
    }

    /**
     * 详情
     */
    @GetMapping("/{id}")
    public Response<Long> detail(@PathVariable("id") Long id) {
        return Response.ok(orderService.detail(id));
    }

    /**
     * 新增
     */
    @PostMapping("add")
    public Response<Long> add(@Validated @RequestBody AddOrderReq req) {
        return Response.ok(orderService.add(req));
    }

    /**
     * 编辑
     */
    @PutMapping("edit")
    public Response<Void> edit() {
        orderService.edit();
        return Response.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/{ids}")
    public Response<Void> delete(@PathVariable Long[] ids) {
        orderService.delete(ids);
        return Response.ok();
    }
}

