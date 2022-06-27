package org.liu.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.liu.product.pojo.Brand;

public interface BrandService extends IService<Brand> {
    Page<Brand> pageList();

    Brand detail(Long id);

    Long add(Brand brand);

    void edit(Brand brand);

    void delete(Long[] ids);
}

