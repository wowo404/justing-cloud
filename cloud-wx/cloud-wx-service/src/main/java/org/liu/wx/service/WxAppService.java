package org.liu.wx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.liu.wx.feign.pojo.po.WxApp;

public interface WxAppService extends IService<WxApp> {
    Page<Long> pageList();

    Long detail(String id);

    Long add();

    void edit();

    void delete(String[] ids);

    WxApp queryByAppId(String appId);

    WxApp queryByWeixinSign(String weixinSign);
}

