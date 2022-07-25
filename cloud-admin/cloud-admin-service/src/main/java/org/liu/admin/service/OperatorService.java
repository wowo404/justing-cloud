package org.liu.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.liu.admin.feign.pojo.OperatorDetailResp;
import org.liu.admin.pojo.Operator;

public interface OperatorService extends IService<Operator> {
    Page<Long> pageList();

    Long detail(Long id);

    Long add();

    void edit();

    void delete(Long[] ids);

    OperatorDetailResp queryByUsername(String username);
}

