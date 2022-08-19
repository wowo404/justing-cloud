package org.liu.wx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.justing.commons.model.Response;
import org.liu.wx.feign.pojo.po.MsgTemplateParam;
import org.liu.wx.service.MsgTemplateParamService;
import org.springframework.web.bind.annotation.*;

/**
 * 订阅消息模板参数
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/wxApi/msgTemplateParam")
public class MsgTemplateParamController {

    private final MsgTemplateParamService msgTemplateParamService;

    /**
     * 分页列表
     */
    @PostMapping("pageList")
    public Response<Page<Long>> pageList() {
        return Response.ok(msgTemplateParamService.pageList());
    }

    /**
     * 详情
     */
    @GetMapping("/{id}")
    public Response<Long> detail(@PathVariable("id") Long id) {
        return Response.ok(msgTemplateParamService.detail(id));
    }

    /**
     * 新增
     */
    @PostMapping("add")
    public Response<Long> add() {
        return Response.ok(msgTemplateParamService.add());
    }

    /**
     * 编辑
     */
    @PutMapping("edit")
    public Response<Void> edit() {
        msgTemplateParamService.edit();
        return Response.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/{ids}")
    public Response<Void> delete(@PathVariable Long[] ids) {
        msgTemplateParamService.delete(ids);
        return Response.ok();
    }
}

