package org.liu.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.liu.admin.feign.pojo.po.Role;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> queryByOperatorId(@Param("operatorId") Long operatorId);
}

