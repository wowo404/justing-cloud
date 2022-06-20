package org.liu.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.justing.commons.model.Response;
import org.liu.product.pojo.Sku;
import org.liu.product.service.SkuService;
import org.springframework.web.bind.annotation.*;

/**
 * sku表,该表表示具体的商品实体,如黑色的64GB的iphone 8
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/sku")
public class SkuController {

    private final SkuService skuService;

    /**
     * 分页列表
     */
    @PostMapping("pageList")
    public Response<Page<Long>> pageList() {
        return Response.ok(skuService.pageList());
    }

    /**
     * 详情
     */
    @GetMapping("/{id}")
    public Response<Long> detail(@PathVariable("id") Long id) {
        return Response.ok(skuService.detail(id));
    }

    /**
     * 新增
     */
    @PostMapping("add")
    public Response<Long> add() {
        return Response.ok(skuService.add());
    }

    /**
     * 编辑
     */
    @PostMapping("edit")
    public Response<Void> edit() {
        skuService.edit();
        return Response.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/{ids}")
    public Response<Void> delete(@PathVariable Long[] ids) {
        skuService.delete(ids);
        return Response.ok();
    }
}

