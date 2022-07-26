package org.liu.product.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.liu.product.mapper.SpecParamMapper;
import org.liu.product.feign.pojo.po.SpecParam;
import org.liu.product.service.SpecParamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Service
public class SpecParamServiceImpl extends ServiceImpl<SpecParamMapper, SpecParam> implements SpecParamService {
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

