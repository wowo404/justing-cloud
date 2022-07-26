package org.liu.auth.service;

import lombok.RequiredArgsConstructor;
import org.justing.commons.exception.CommonException;
import org.justing.commons.model.Response;
import org.liu.admin.feign.client.OperatorClient;
import org.liu.admin.feign.pojo.enums.CredentialsExpiredEnum;
import org.liu.admin.feign.pojo.po.Menu;
import org.liu.admin.feign.pojo.po.Role;
import org.liu.admin.feign.pojo.resp.OperatorDetailResp;
import org.liu.common.core.enums.ClientEnum;
import org.liu.common.core.enums.OperatorStatusEnum;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static org.liu.auth.exception.BizCodeEnum.ERROR_GET_OPERATOR_DETAIL;
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
        String client = getRequest().getHeader(HEADER_CLIENT);
        if (!StringUtils.hasText(client)) {
            throw new CommonException(MISSING_HEADER_CLIENT);
        }
        if (client.equals(ClientEnum.PC.name())) {
            Response<OperatorDetailResp> response = operatorClient.queryByUsername(username);
            if (!response.isOk()) {
                throw new CommonException(ERROR_GET_OPERATOR_DETAIL);
            }
            OperatorDetailResp operator = response.getData();
            return User.builder()
                    .username(operator.getUsername())
                    .password(operator.getPassword())
                    .accountLocked(OperatorStatusEnum.LOCKED.getCode().equals(operator.getStatus()))
                    .disabled(false)
                    .accountExpired(false)
                    .credentialsExpired(CredentialsExpiredEnum.NO.getCode().equals(operator.getCredentialsExpired()))
                    .roles(operator.getRoles().stream().map(Role::getCode).toArray(String[]::new))
                    .authorities(AuthorityUtils.createAuthorityList(operator.getMenus().stream().map(Menu::getUrl).distinct().toArray(String[]::new)))
                    .build();
        } else {

        }
        return null;
    }

    private HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return requestAttributes.getRequest();
    }

}
