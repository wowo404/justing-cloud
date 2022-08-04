package org.liu.wx.exception;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.justing.commons.model.Response;
import org.liu.common.service.util.ServletUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.liu.wx.feign.pojo.exception.BizCodeEnum.WX_ERROR;

/**
 * @Author lzs
 * @Date 2022/8/4 15:06
 **/
@Slf4j
@ControllerAdvice
public class WxExceptionHandler {

    @ExceptionHandler(WxErrorException.class)
    public Response<Void> WxErrorException(WxErrorException e){
        log.error("异常的uri={}", ServletUtils.getRequest().getRequestURI());
        log.error("微信接口异常", e);
        return Response.error(WX_ERROR, e.getError().getErrorMsg());
    }

}
