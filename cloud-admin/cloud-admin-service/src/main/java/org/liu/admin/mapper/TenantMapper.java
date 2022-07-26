package org.liu.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.liu.admin.feign.pojo.po.Tenant;

@Mapper
public interface TenantMapper extends BaseMapper<Tenant> {
}

