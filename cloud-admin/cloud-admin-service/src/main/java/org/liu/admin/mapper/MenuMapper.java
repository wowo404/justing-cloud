package org.liu.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.liu.admin.feign.pojo.MenuDetailResp;
import org.liu.admin.pojo.Menu;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    List<MenuDetailResp> queryByOperatorId(@Param("operatorId") Long operatorId);
}

