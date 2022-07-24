package org.liu.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.justing.commons.model.Response;
import org.liu.admin.service.TenantService;
import org.springframework.web.bind.annotation.*;

/**
 * 租户表
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/tenant")
public class TenantController {

    private final TenantService tenantService;

    /**
     * 分页列表
     */
    @PostMapping("pageList")
    public Response<Page<Long>> pageList() {
        return Response.ok(tenantService.pageList());
    }

    /**
     * 详情
     */
    @GetMapping("/{id}")
    public Response<Long> detail(@PathVariable("id") Long id) {
        return Response.ok(tenantService.detail(id));
    }

    /**
     * 新增
     */
    @PostMapping("add")
    public Response<Long> add() {
        return Response.ok(tenantService.add());
    }

    /**
     * 编辑
     */
    @PutMapping("edit")
    public Response<Void> edit() {
        tenantService.edit();
        return Response.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/{ids}")
    public Response<Void> delete(@PathVariable Long[] ids) {
        tenantService.delete(ids);
        return Response.ok();
    }
}

