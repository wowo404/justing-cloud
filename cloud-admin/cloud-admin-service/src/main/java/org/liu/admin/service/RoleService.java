package org.liu.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.liu.admin.feign.pojo.RoleDetailResp;
import org.liu.admin.pojo.Role;

import java.util.List;

public interface RoleService extends IService<Role> {
    Page<Long> pageList();

    Long detail(Long id);

    Long add();

    void edit();

    void delete(Long[] ids);

    List<RoleDetailResp> queryByOperatorId(Long operatorId);
}

