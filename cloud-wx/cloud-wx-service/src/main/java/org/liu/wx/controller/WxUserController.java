package org.liu.wx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.justing.commons.model.Response;
import org.liu.wx.feign.pojo.po.WxUser;
import org.liu.wx.service.WxUserService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 微信用户
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/wxUser")
public class WxUserController {

    private final WxUserService wxUserService;

    /**
     * 分页列表
     */
    @PostMapping("pageList")
    public Response<Page<Long>> pageList() {
        return Response.ok(wxUserService.pageList());
    }

    /**
     * 详情
     */
    @GetMapping("/{id}")
    public Response<Long> detail(@PathVariable("id") Long id) {
        return Response.ok(wxUserService.detail(id));
    }

    /**
     * 新增
     */
    @PostMapping("add")
    public Response<Long> add() {
        return Response.ok(wxUserService.add());
    }

    /**
     * 编辑
     */
    @PutMapping("edit")
    public Response<Void> edit() {
        wxUserService.edit();
        return Response.ok();
    }

    /**
     * 删除
     */
    @DeleteMapping("/{ids}")
    public Response<Void> delete(@PathVariable Long[] ids) {
        wxUserService.delete(ids);
        return Response.ok();
    }

    @GetMapping("wxUser/{openId}")
    public Response<WxUser> queryByOpenId(@PathVariable("openId") String openId){
        return Response.ok(wxUserService.queryByOpenId(openId));
    }

    @GetMapping("wxUser/{jsCode}")
    public Response<WxUser> queryByJsCode(@PathVariable("jsCode") String jsCode, HttpServletRequest request){
        return Response.ok(wxUserService.queryByJsCode(jsCode, request));
    }
}

