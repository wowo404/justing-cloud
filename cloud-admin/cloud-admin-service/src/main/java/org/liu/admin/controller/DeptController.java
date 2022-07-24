package org.liu.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.justing.commons.model.Response;
import org.liu.admin.service.DeptService;
import org.springframework.web.bind.annotation.*;

/**
 * 部门表
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/dept")
public class DeptController {

    private final DeptService deptService;

    /**
     * 分页列表
     */
    @PostMapping("pageList")
    public Response<Page<Long>> pageList() {
        return Response.ok(deptService.pageList());
    }

    /**
     * 详情
     */
    @GetMapping("/{id}")
    public Response<Long> detail(@PathVariable("id") Long id) {
        return Response.ok(deptService.detail(id));
    }

    /**
     * 新增
     */
    @PostMapping("add")
    public Response<Long> add() {
        return Response.ok(deptService.add());
    }

    /**
     * 编辑
     */
    @PutMapping("edit")
    public Response<Void> edit() {
        deptService.edit();
        return Response.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/{ids}")
    public Response<Void> delete(@PathVariable Long[] ids) {
        deptService.delete(ids);
        return Response.ok();
    }
}

