package org.liu.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.liu.product.feign.pojo.po.Sku;

@Mapper
public interface SkuMapper extends BaseMapper<Sku> {
}

