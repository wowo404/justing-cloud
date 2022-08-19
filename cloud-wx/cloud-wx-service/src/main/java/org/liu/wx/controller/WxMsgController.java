package org.liu.wx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.justing.commons.model.Response;
import org.liu.wx.feign.pojo.po.WxMsg;
import org.liu.wx.service.WxMsgService;
import org.springframework.web.bind.annotation.*;

/**
 * 微信消息
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/wxApi/wxMsg")
public class WxMsgController {

    private final WxMsgService wxMsgService;

    /**
     * 分页列表
     */
    @PostMapping("pageList")
    public Response<Page<Long>> pageList() {
        return Response.ok(wxMsgService.pageList());
    }

    /**
     * 详情
     */
    @GetMapping("/{id}")
    public Response<Long> detail(@PathVariable("id") Long id) {
        return Response.ok(wxMsgService.detail(id));
    }

    /**
     * 新增
     */
    @PostMapping("add")
    public Response<Long> add() {
        return Response.ok(wxMsgService.add());
    }

    /**
     * 编辑
     */
    @PutMapping("edit")
    public Response<Void> edit() {
        wxMsgService.edit();
        return Response.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/{ids}")
    public Response<Void> delete(@PathVariable Long[] ids) {
        wxMsgService.delete(ids);
        return Response.ok();
    }
}

