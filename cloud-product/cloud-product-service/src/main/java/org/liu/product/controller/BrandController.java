package org.liu.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.justing.commons.model.Response;
import org.liu.product.pojo.Brand;
import org.liu.product.service.BrandService;
import org.springframework.web.bind.annotation.*;

/**
 * 品牌表，一个品牌下有多个商品（spu），一对多关系
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/brand")
public class BrandController {

    private final BrandService brandService;

    /**
     * 分页列表
     */
    @PostMapping("pageList")
    public Response<Page<Brand>> pageList() {
        return Response.ok(brandService.pageList());
    }

    /**
     * 详情
     */
    @GetMapping("/{id}")
    public Response<Brand> detail(@PathVariable("id") Long id) {
        return Response.ok(brandService.detail(id));
    }

    /**
     * 新增
     */
    @PostMapping("add")
    public Response<Long> add(Brand brand) {
        return Response.ok(brandService.add(brand));
    }

    /**
     * 编辑
     */
    @PutMapping("edit")
    public Response<Void> edit(Brand brand) {
        brandService.edit(brand);
        return Response.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/{ids}")
    public Response<Void> delete(@PathVariable Long[] ids) {
        brandService.delete(ids);
        return Response.ok();
    }
}

