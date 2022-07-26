package org.liu.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.justing.commons.model.Response;
import org.liu.admin.feign.pojo.po.OperatorRole;
import org.liu.admin.service.OperatorRoleService;
import org.springframework.web.bind.annotation.*;

/**
 * 操作员和角色的关系表
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/operatorRole")
public class OperatorRoleController {

    private final OperatorRoleService operatorRoleService;

    /**
     * 分页列表
     */
    @PostMapping("pageList")
    public Response<Page<Long>> pageList() {
        return Response.ok(operatorRoleService.pageList());
    }

    /**
     * 详情
     */
    @GetMapping("/{id}")
    public Response<Long> detail(@PathVariable("id") Long id) {
        return Response.ok(operatorRoleService.detail(id));
    }

    /**
     * 新增
     */
    @PostMapping("add")
    public Response<Long> add() {
        return Response.ok(operatorRoleService.add());
    }

    /**
     * 编辑
     */
    @PutMapping("edit")
    public Response<Void> edit() {
        operatorRoleService.edit();
        return Response.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/{ids}")
    public Response<Void> delete(@PathVariable Long[] ids) {
        operatorRoleService.delete(ids);
        return Response.ok();
    }
}

