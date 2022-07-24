package org.liu.auth.service;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import org.justing.commons.exception.CommonException;
import org.liu.admin.feign.client.OperatorClient;
import org.liu.common.core.enums.ClientEnum;
import org.liu.common.springmvc.utils.ServletUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import static org.liu.auth.exception.BizCodeEnum.MISSING_HEADER_CLIENT;
import static org.liu.common.core.constants.CommonConstants.HEADER_CLIENT;

/**
 * @Author lzs
 * @Date 2022/7/13 17:51
 **/
@RequiredArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final OperatorClient operatorClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String client = ServletUtil.getRequest().getHeader(HEADER_CLIENT);
        if (StrUtil.isBlank(client)) {
            throw new CommonException(MISSING_HEADER_CLIENT);
        }
        if (client.equals(ClientEnum.PC.name())) {
            operatorClient.queryByUsername(username);
        } else {

        }
        return null;
    }
}
