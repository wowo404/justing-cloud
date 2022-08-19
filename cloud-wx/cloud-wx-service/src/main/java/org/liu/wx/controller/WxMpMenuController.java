package org.liu.wx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.justing.commons.model.Response;
import org.liu.wx.feign.pojo.po.WxMpMenu;
import org.liu.wx.service.WxMpMenuService;
import org.springframework.web.bind.annotation.*;

/**
 * 自定义公众号菜单表
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/wxApi/wxMpMenu")
public class WxMpMenuController {

    private final WxMpMenuService wxMpMenuService;

    /**
     * 分页列表
     */
    @PostMapping("pageList")
    public Response<Page<Long>> pageList() {
        return Response.ok(wxMpMenuService.pageList());
    }

    /**
     * 详情
     */
    @GetMapping("/{id}")
    public Response<Long> detail(@PathVariable("id") Long id) {
        return Response.ok(wxMpMenuService.detail(id));
    }

    /**
     * 新增
     */
    @PostMapping("add")
    public Response<Long> add() {
        return Response.ok(wxMpMenuService.add());
    }

    /**
     * 编辑
     */
    @PutMapping("edit")
    public Response<Void> edit() {
        wxMpMenuService.edit();
        return Response.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/{ids}")
    public Response<Void> delete(@PathVariable Long[] ids) {
        wxMpMenuService.delete(ids);
        return Response.ok();
    }
}

