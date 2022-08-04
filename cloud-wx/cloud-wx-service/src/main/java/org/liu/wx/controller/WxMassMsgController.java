package org.liu.wx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.justing.commons.model.Response;
import org.liu.wx.feign.pojo.po.WxMassMsg;
import org.liu.wx.service.WxMassMsgService;
import org.springframework.web.bind.annotation.*;

/**
 * 群发消息表
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/wxMassMsg")
public class WxMassMsgController {

    private final WxMassMsgService wxMassMsgService;

    /**
     * 分页列表
     */
    @PostMapping("pageList")
    public Response<Page<Long>> pageList() {
        return Response.ok(wxMassMsgService.pageList());
    }

    /**
     * 详情
     */
    @GetMapping("/{id}")
    public Response<Long> detail(@PathVariable("id") Long id) {
        return Response.ok(wxMassMsgService.detail(id));
    }

    /**
     * 新增
     */
    @PostMapping("add")
    public Response<Long> add() {
        return Response.ok(wxMassMsgService.add());
    }

    /**
     * 编辑
     */
    @PutMapping("edit")
    public Response<Void> edit() {
        wxMassMsgService.edit();
        return Response.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/{ids}")
    public Response<Void> delete(@PathVariable Long[] ids) {
        wxMassMsgService.delete(ids);
        return Response.ok();
    }
}

