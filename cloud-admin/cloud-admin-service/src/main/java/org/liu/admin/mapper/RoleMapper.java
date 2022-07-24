package org.liu.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.liu.admin.feign.pojo.Role;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}

