package org.liu.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.liu.order.feign.pojo.po.OrderItem;

@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {
}

