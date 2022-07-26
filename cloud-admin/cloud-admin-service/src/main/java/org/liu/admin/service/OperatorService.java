package org.liu.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.liu.admin.feign.pojo.po.Operator;
import org.liu.admin.feign.pojo.resp.OperatorDetailResp;

public interface OperatorService extends IService<Operator> {
    Page<Long> pageList();

    Long detail(Long id);

    Long add();

    void edit();

    void delete(Long[] ids);

    OperatorDetailResp queryByUsername(String username);
}

