package org.liu.wx.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.liu.wx.feign.pojo.po.WxApp;
import org.liu.wx.mapper.WxAppMapper;
import org.liu.wx.service.WxAppService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Service
public class WxAppServiceImpl extends ServiceImpl<WxAppMapper, WxApp> implements WxAppService {
    @Override
    public Page<Long> pageList() {
        return null;
    }

    @Override
    public Long detail(String id) {
        return null;
    }

    @Override
    public Long add() {
        return null;
    }

    @Override
    public void edit() {
    }

    @Override
    public void delete(String[] ids) {
    }

    @Override
    public WxApp queryByAppId(String appId) {
        return super.getOne(Wrappers.lambdaQuery(WxApp.class).eq(WxApp::getAppId, appId));
    }

    @Override
    public WxApp queryByWeixinSign(String weixinSign) {
        return super.getOne(Wrappers.lambdaQuery(WxApp.class).eq(WxApp::getWeixinSign, weixinSign));
    }
}

