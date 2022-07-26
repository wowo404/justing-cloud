package org.liu.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.liu.admin.feign.pojo.po.Menu;

import java.util.List;

public interface MenuService extends IService<Menu> {
    Page<Long> pageList();

    Long detail(Long id);

    Long add();

    void edit();

    void delete(Long[] ids);

    List<Menu> queryByOperatorId(Long operatorId);
}

