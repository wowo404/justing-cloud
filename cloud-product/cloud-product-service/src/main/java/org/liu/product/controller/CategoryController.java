package org.liu.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.justing.commons.model.Response;
import org.liu.product.service.CategoryService;
import org.springframework.web.bind.annotation.*;

/**
 * 商品类目表，类目和商品(spu)是一对多关系，类目与品牌是多对多关系
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * 分页列表
     */
    @PostMapping("pageList")
    public Response<Page<Long>> pageList() {
        return Response.ok(categoryService.pageList());
    }

    /**
     * 详情
     */
    @GetMapping("/{id}")
    public Response<Long> detail(@PathVariable("id") Long id) {
        return Response.ok(categoryService.detail(id));
    }

    /**
     * 新增
     */
    @PostMapping("add")
    public Response<Long> add() {
        return Response.ok(categoryService.add());
    }

    /**
     * 编辑
     */
    @PutMapping("edit")
    public Response<Void> edit() {
        categoryService.edit();
        return Response.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/{ids}")
    public Response<Void> delete(@PathVariable Long[] ids) {
        categoryService.delete(ids);
        return Response.ok();
    }
}

