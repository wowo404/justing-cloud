package org.liu.common.feign.fallback;

import feign.FeignException;
import feign.hystrix.FallbackFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.justing.commons.enums.CommonCodeEnum;
import org.justing.commons.model.Response;
import org.justing.commons.util.JacksonUtil;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * TIPS：此类还没有使用
 * fallback统一处理方法一：
 * 1.使用jdk动态代理创建FallbackFactory的实现类
 * 2.在@FeignClient注解上配置fallbackFactory=DynamicGlobalFallbackFactory.class
 * 3.还可以参考joolun项目中base-common-security模块下的openfeign实现添加targeter实现和使用configuration进行自动配置
 * 4.如果实现了第3点，则在@FeignClient注解上不需要配置fallback或fallbackFactory，只在需要特殊处理进行配置，另外还需要注意包的路径必须跟openfeign包的路径一致
 *
 * 处理方法二：
 * 1.不使用jdk动态代理，使用spring对cglib的增强实现Enhancer
 * 2.参考joolun项目中base-common-security模块下的openfeign实现
 *
 * 处理方法三（不推荐）：
 * 1.如上面两个方法一样，创建FallbackFactory的实现类
 * 2.在实现类里写上每一个feign接口中的方法的对应处理逻辑
 *
 * @author lzs
 * @Date 2024/5/6 11:10
 **/
@Slf4j
@AllArgsConstructor
public class DynamicGlobalFallbackFactory<T> implements FallbackFactory<T> {

    private Class<T> targetType;

    @Override
    public T create(Throwable cause) {
        return (T) Proxy.newProxyInstance(targetType.getClassLoader(), targetType.getInterfaces(), (proxy, method, args) -> {
            try {
                return method.invoke(proxy, args);
            } catch (Exception e) {
                return handleException(method, cause);
            }
        });
    }

    private Object handleException(Method method, Throwable cause) {
        if (method.getReturnType() != Response.class) {
            return Response.error(CommonCodeEnum.ERROR_RETURN_TYPE);
        }
        FeignException fe = (FeignException) cause;
        String errMsg = fe.contentUTF8();
        log.error("target:{}.{} cause fallback,,message:{}", targetType.getName(), method.getName(), errMsg);
        return JacksonUtil.stringToObject(errMsg, Response.class);
    }
}
