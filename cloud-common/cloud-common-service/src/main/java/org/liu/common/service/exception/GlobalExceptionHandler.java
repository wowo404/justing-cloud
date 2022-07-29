package org.liu.common.service.exception;

import org.justing.commons.enums.CommonCodeEnum;
import org.justing.commons.exception.CommonException;
import org.justing.commons.model.Response;
import org.liu.common.service.util.ServletUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;

/**
 * 全局异常处理器
 *
 * @author ruoyi
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 业务异常
     */
    @ExceptionHandler(CommonException.class)
    public Response<Void> baseException(CommonException e) {
        log.error("异常的uri={}", ServletUtils.getRequest().getRequestURI());
        log.error("业务异常", e);
        return Response.error(e.getCodeEnum());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Response<Void> handlerNoFoundException(NoHandlerFoundException e) {
        log.error("异常的uri={}", ServletUtils.getRequest().getRequestURI());
        log.error(e.getMessage(), e);
        return Response.error(CommonCodeEnum.SESSION_INVALID);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Response<Void> handleAccessDeniedException(AccessDeniedException e) {
        log.error("异常的uri={}", ServletUtils.getRequest().getRequestURI());
        log.error(e.getMessage());
        return Response.error(CommonCodeEnum.UNAUTHORIZED_ERROR);
    }

    @ExceptionHandler(AuthenticationException.class)
    public Response<Void> handleAuthenticationException(AuthenticationException e) {
        log.error("异常的uri={}", ServletUtils.getRequest().getRequestURI());
        log.error(e.getMessage(), e);
        return Response.error(CommonCodeEnum.LOGIN_FAIL);
    }

    @ExceptionHandler(Exception.class)
    public Response<Void> handleException(Exception e) {
        log.error("异常的uri={}", ServletUtils.getRequest().getRequestURI());
        log.error(e.getMessage(), e);
        return Response.error(CommonCodeEnum.SERVER_ERROR);
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public Response<Void> validatedBindException(BindException e) {
        log.error("异常的uri={}", ServletUtils.getRequest().getRequestURI());
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return Response.error(CommonCodeEnum.PARAM_ERROR.code, message);
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Response<Void> constraintViolationException(ConstraintViolationException e) {
        log.error("异常的uri={}", ServletUtils.getRequest().getRequestURI());
        log.error(e.getMessage(), e);
        String message = e.getConstraintViolations().iterator().next().getMessage();
        return Response.error(CommonCodeEnum.PARAM_ERROR.code, message);
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response<Void> validExceptionHandler(MethodArgumentNotValidException e) {
        log.error("异常的uri={}", ServletUtils.getRequest().getRequestURI());
        log.error(e.getMessage(), e);
        FieldError fieldError = e.getBindingResult().getFieldError();
        if (null == fieldError) {
            return Response.error(CommonCodeEnum.PARAM_ERROR);
        }
        return Response.error(CommonCodeEnum.PARAM_ERROR.code, fieldError.getDefaultMessage());
    }

}
