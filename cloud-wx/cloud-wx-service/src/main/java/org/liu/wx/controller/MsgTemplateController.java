package org.liu.wx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.justing.commons.model.Response;
import org.liu.wx.feign.pojo.po.MsgTemplate;
import org.liu.wx.service.MsgTemplateService;
import org.springframework.web.bind.annotation.*;

/**
 * 订阅消息模板
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/msgTemplate")
public class MsgTemplateController {

    private final MsgTemplateService msgTemplateService;

    /**
     * 分页列表
     */
    @PostMapping("pageList")
    public Response<Page<Long>> pageList() {
        return Response.ok(msgTemplateService.pageList());
    }

    /**
     * 详情
     */
    @GetMapping("/{id}")
    public Response<Long> detail(@PathVariable("id") Long id) {
        return Response.ok(msgTemplateService.detail(id));
    }

    /**
     * 新增
     */
    @PostMapping("add")
    public Response<Long> add() {
        return Response.ok(msgTemplateService.add());
    }

    /**
     * 编辑
     */
    @PutMapping("edit")
    public Response<Void> edit() {
        msgTemplateService.edit();
        return Response.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/{ids}")
    public Response<Void> delete(@PathVariable Long[] ids) {
        msgTemplateService.delete(ids);
        return Response.ok();
    }
}

