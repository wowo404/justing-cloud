package org.liu.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.liu.admin.feign.pojo.RoleDetailResp;
import org.liu.admin.pojo.Role;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    List<RoleDetailResp> queryByOperatorId(@Param("operatorId") Long operatorId);
}

