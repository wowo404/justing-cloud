package org.liu.wx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.liu.wx.feign.pojo.po.WxMpMenu;

@Mapper
public interface WxMpMenuMapper extends BaseMapper<WxMpMenu> {
}

