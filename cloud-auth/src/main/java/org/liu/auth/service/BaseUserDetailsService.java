package org.liu.auth.service;

import org.liu.common.core.enums.ClientEnum;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Author lzs
 * @Date 2022/8/3 14:51
 **/
public interface BaseUserDetailsService extends UserDetailsService {
    Boolean supports(ClientEnum client);
}