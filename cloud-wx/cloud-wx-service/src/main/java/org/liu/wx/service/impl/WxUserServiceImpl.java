package org.liu.wx.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.justing.commons.exception.CommonException;
import org.liu.wx.config.ma.WxMaConfiguration;
import org.liu.wx.feign.pojo.po.WxUser;
import org.liu.wx.mapper.WxUserMapper;
import org.liu.wx.service.WxUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

import static org.liu.wx.feign.pojo.exception.BizCodeEnum.WX_ERROR_GET_SESSION_INFO;

@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Service
public class WxUserServiceImpl extends ServiceImpl<WxUserMapper, WxUser> implements WxUserService {
    @Override
    public Page<Long> pageList() {
        return null;
    }

    @Override
    public Long detail(Long id) {
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
    public void delete(Long[] ids) {
    }

    @Override
    public WxUser queryByOpenId(String openId) {
        return super.lambdaQuery().eq(WxUser::getOpenId, openId).one();
    }

    @Override
    public WxUser queryByJsCode(String jsCode, HttpServletRequest request) throws WxErrorException {
        WxMaService maService = WxMaConfiguration.getMaService(request);
        WxMaJscode2SessionResult sessionInfo = maService.getUserService().getSessionInfo(jsCode);
        if (sessionInfo == null) {
            throw new CommonException(WX_ERROR_GET_SESSION_INFO);
        }
        return queryByOpenId(sessionInfo.getOpenid());
    }
}

