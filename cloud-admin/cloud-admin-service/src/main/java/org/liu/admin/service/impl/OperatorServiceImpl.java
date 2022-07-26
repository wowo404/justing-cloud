package org.liu.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.liu.admin.feign.pojo.po.Menu;
import org.liu.admin.feign.pojo.po.Operator;
import org.liu.admin.feign.pojo.po.Role;
import org.liu.admin.feign.pojo.resp.OperatorDetailResp;
import org.liu.admin.mapper.OperatorMapper;
import org.liu.admin.service.MenuService;
import org.liu.admin.service.OperatorService;
import org.liu.admin.service.RoleService;
import org.liu.common.core.enums.DeletedEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Service
public class OperatorServiceImpl extends ServiceImpl<OperatorMapper, Operator> implements OperatorService {

    private final RoleService roleService;
    private final MenuService menuService;

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
    public OperatorDetailResp queryByUsername(String username) {
        Operator operator = super.lambdaQuery()
                .ge(Operator::getUsername, username)
                .ge(Operator::getDeleted, DeletedEnum.EXISTS.getCode())
                .one();
        List<Role> roles = roleService.queryByOperatorId(operator.getId());
        List<Menu> menus = menuService.queryByOperatorId(operator.getId());
        OperatorDetailResp resp = BeanUtil.copyProperties(operator, OperatorDetailResp.class);
        resp.setRoles(roles);
        resp.setMenus(menus);
        return resp;
    }
}

