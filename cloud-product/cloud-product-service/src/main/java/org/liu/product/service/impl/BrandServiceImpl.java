package org.liu.product.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.liu.product.mapper.BrandMapper;
import org.liu.product.pojo.Brand;
import org.liu.product.service.BrandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {
    @Override
    public Page<Brand> pageList() {
        return null;
    }

    @Override
    public Brand detail(Long id) {
        return super.getById(id);
    }

    @Override
    public Long add(Brand brand) {
        super.save(brand);
        return brand.getId();
    }

    @Override
    public void edit(Brand brand) {
        super.updateById(brand);
    }

    @Override
    public void delete(Long[] ids) {
        super.removeByIds(Arrays.asList(ids));
    }
}

