package org.liu.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.liu.admin.feign.pojo.Dept;

public interface DeptService extends IService<Dept> {
    Page<Long> pageList();

    Long detail(Long id);

    Long add();

    void edit();

    void delete(Long[] ids);
}

