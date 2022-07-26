package org.liu.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.justing.commons.model.Response;
import org.liu.product.service.SpuDetailService;
import org.springframework.web.bind.annotation.*;

/**
 * 商品详情
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/spuDetail")
public class SpuDetailController {

    private final SpuDetailService spuDetailService;

    /**
     * 分页列表
     */
    @PostMapping("pageList")
    public Response<Page<Long>> pageList() {
        return Response.ok(spuDetailService.pageList());
    }

    /**
     * 详情
     */
    @GetMapping("/{id}")
    public Response<Long> detail(@PathVariable("id") Long id) {
        return Response.ok(spuDetailService.detail(id));
    }

    /**
     * 新增
     */
    @PostMapping("add")
    public Response<Long> add() {
        return Response.ok(spuDetailService.add());
    }

    /**
     * 编辑
     */
    @PutMapping("edit")
    public Response<Void> edit() {
        spuDetailService.edit();
        return Response.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/{ids}")
    public Response<Void> delete(@PathVariable Long[] ids) {
        spuDetailService.delete(ids);
        return Response.ok();
    }
}

