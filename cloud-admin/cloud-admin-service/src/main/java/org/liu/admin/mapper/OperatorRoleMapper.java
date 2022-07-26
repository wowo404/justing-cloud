package org.liu.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.liu.admin.feign.pojo.po.OperatorRole;

@Mapper
public interface OperatorRoleMapper extends BaseMapper<OperatorRole> {
}

