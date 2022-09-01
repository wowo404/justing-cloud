package org.liu.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.liu.admin.feign.pojo.po.Role;
import org.liu.admin.feign.pojo.req.RoleListReq;

import java.util.List;

public interface RoleService extends IService<Role> {
    Page<Role> pageList(RoleListReq req);

    Role detail(Long id);

    Long add();

    void edit();

    void delete(Long[] ids);

    List<Role> queryByOperatorId(Long operatorId);
}

