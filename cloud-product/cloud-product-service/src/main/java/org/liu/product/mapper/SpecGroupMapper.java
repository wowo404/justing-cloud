package org.liu.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.liu.product.feign.pojo.po.SpecGroup;

@Mapper
public interface SpecGroupMapper extends BaseMapper<SpecGroup> {
}

