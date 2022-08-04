package org.liu.wx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.justing.commons.model.Response;
import org.liu.wx.feign.pojo.po.WxApp;
import org.liu.wx.service.WxAppService;
import org.springframework.web.bind.annotation.*;

/**
 * 微信应用
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/wxApp")
public class WxAppController {

    private final WxAppService wxAppService;

    /**
     * 分页列表
     */
    @PostMapping("pageList")
    public Response<Page<Long>> pageList() {
        return Response.ok(wxAppService.pageList());
    }

    /**
     * 详情
     */
    @GetMapping("/{id}")
    public Response<Long> detail(@PathVariable("id") String id) {
        return Response.ok(wxAppService.detail(id));
    }

    /**
     * 新增
     */
    @PostMapping("add")
    public Response<Long> add() {
        return Response.ok(wxAppService.add());
    }

    /**
     * 编辑
     */
    @PutMapping("edit")
    public Response<Void> edit() {
        wxAppService.edit();
        return Response.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/{ids}")
    public Response<Void> delete(@PathVariable String[] ids) {
        wxAppService.delete(ids);
        return Response.ok();
    }

    @GetMapping("queryByAppId/{appId}")
    public Response<WxApp> queryByAppId(@PathVariable String appId){
        return Response.ok(wxAppService.queryByAppId(appId));
    }
}

