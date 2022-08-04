package org.liu.wx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import me.chanjar.weixin.common.error.WxErrorException;
import org.liu.wx.feign.pojo.po.WxUser;

import javax.servlet.http.HttpServletRequest;

public interface WxUserService extends IService<WxUser> {
    Page<Long> pageList();

    Long detail(Long id);

    Long add();

    void edit();

    void delete(Long[] ids);

    WxUser queryByOpenId(String openId);

    WxUser queryByJsCode(String jsCode, HttpServletRequest request) throws WxErrorException;
}

