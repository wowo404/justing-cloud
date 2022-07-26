package org.liu.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.justing.commons.model.Response;
import org.liu.product.service.CategoryBrandService;
import org.springframework.web.bind.annotation.*;

/**
 * 商品分类和品牌的中间表，两者是多对多关系
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/categoryBrand")
public class CategoryBrandController {

    private final CategoryBrandService categoryBrandService;

    /**
     * 分页列表
     */
    @PostMapping("pageList")
    public Response<Page<Long>> pageList() {
        return Response.ok(categoryBrandService.pageList());
    }

    /**
     * 详情
     */
    @GetMapping("/{id}")
    public Response<Long> detail(@PathVariable("id") Long id) {
        return Response.ok(categoryBrandService.detail(id));
    }

    /**
     * 新增
     */
    @PostMapping("add")
    public Response<Long> add() {
        return Response.ok(categoryBrandService.add());
    }

    /**
     * 编辑
     */
    @PutMapping("edit")
    public Response<Void> edit() {
        categoryBrandService.edit();
        return Response.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/{ids}")
    public Response<Void> delete(@PathVariable Long[] ids) {
        categoryBrandService.delete(ids);
        return Response.ok();
    }
}

