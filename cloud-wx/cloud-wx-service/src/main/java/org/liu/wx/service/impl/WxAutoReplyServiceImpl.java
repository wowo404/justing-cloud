package org.liu.wx.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.liu.wx.feign.pojo.po.WxAutoReply;
import org.liu.wx.mapper.WxAutoReplyMapper;
import org.liu.wx.service.WxAutoReplyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Service
public class WxAutoReplyServiceImpl extends ServiceImpl<WxAutoReplyMapper, WxAutoReply> implements WxAutoReplyService {
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
}

