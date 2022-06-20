package org.liu.product.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.justing.commons.model.Response;
import org.liu.product.pojo.SpecGroup;
import org.liu.product.service.SpecGroupService;
import org.springframework.web.bind.annotation.*;

/**
 * 规格参数的分组表，每个商品分类下有多个规格参数组
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/specGroup")
public class SpecGroupController {

    private final SpecGroupService specGroupService;

    /**
     * 分页列表
     */
    @PostMapping("pageList")
    public Response<Page<Long>> pageList() {
        return Response.ok(specGroupService.pageList());
    }

    /**
     * 详情
     */
    @GetMapping("/{id}")
    public Response<Long> detail(@PathVariable("id") Long id) {
        return Response.ok(specGroupService.detail(id));
    }

    /**
     * 新增
     */
    @PostMapping("add")
    public Response<Long> add() {
        return Response.ok(specGroupService.add());
    }

    /**
     * 编辑
     */
    @PostMapping("edit")
    public Response<Void> edit() {
        specGroupService.edit();
        return Response.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/{ids}")
    public Response<Void> delete(@PathVariable Long[] ids) {
        specGroupService.delete(ids);
        return Response.ok();
    }
}

