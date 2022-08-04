package org.liu.wx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.liu.wx.feign.pojo.po.MsgTemplateParam;

public interface MsgTemplateParamService extends IService<MsgTemplateParam> {
    Page<Long> pageList();

    Long detail(Long id);

    Long add();

    void edit();

    void delete(Long[] ids);
}

