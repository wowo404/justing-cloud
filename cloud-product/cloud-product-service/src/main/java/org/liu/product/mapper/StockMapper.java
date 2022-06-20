package org.liu.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.liu.product.pojo.Stock;

@Mapper
public interface StockMapper extends BaseMapper<Stock> {
}

