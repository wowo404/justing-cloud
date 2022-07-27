package org.liu.common.security.resource.server.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Author lzs
 * @Date 2022/7/27 14:40
 **/
public class SecurityUtils {

    public static Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }



}
