package org.liu.wx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.liu.wx.feign.pojo.po.MsgTemplateParam;

@Mapper
public interface MsgTemplateParamMapper extends BaseMapper<MsgTemplateParam> {
}

