package org.liu.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.liu.admin.feign.pojo.po.Role;
import org.liu.admin.feign.pojo.req.RoleListReq;
import org.liu.admin.mapper.RoleMapper;
import org.liu.admin.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Override
    public Page<Role> pageList(RoleListReq req) {
        Page<Role> page = new Page<>(req.getPageNum(), req.getPageSize());
        return super.page(page, Wrappers.lambdaQuery(Role.class)
                .like(StrUtil.isNotBlank(req.getName()), Role::getName, req.getName())
                .like(StrUtil.isNotBlank(req.getCode()), Role::getCode, req.getCode())
                .orderByDesc(Role::getCreateTime));
    }

    @Override
    public Role detail(Long id) {
        return super.getById(id);
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
    public List<Role> queryByOperatorId(Long operatorId) {
        return baseMapper.queryByOperatorId(operatorId);
    }
}

