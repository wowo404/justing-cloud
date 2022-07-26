package org.liu.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.liu.admin.feign.pojo.po.OperateLog;

@Mapper
public interface OperateLogMapper extends BaseMapper<OperateLog> {
}

