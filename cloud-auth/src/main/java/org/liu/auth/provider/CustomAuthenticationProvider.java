package org.liu.auth.provider;

import org.justing.commons.enums.CommonCodeEnum;
import org.justing.commons.exception.CommonException;
import org.liu.auth.authentication.CustomWebAuthenticationDetails;
import org.liu.auth.service.BaseUserDetailsService;
import org.liu.common.core.enums.ClientEnum;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static org.liu.common.core.constants.CommonConstants.HEADER_CLIENT;

/**
 * WARNING:client_credentials模式不会走此步骤
 *
 * @Author lzs
 * @Date 2022/8/3 15:32
 **/
public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private static final String USER_NOT_FOUND_PASSWORD = "userNotFoundPassword";
    private PasswordEncoder passwordEncoder;
    private volatile String userNotFoundEncodedPassword;
    private List<BaseUserDetailsService> userDetailsServices;
    private UserDetailsPasswordService userDetailsPasswordService;

    public CustomAuthenticationProvider() {
        setPasswordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
    }

    @Override
    protected void doAfterPropertiesSet() {
        Assert.notEmpty(this.userDetailsServices, "A UserDetailsServices must be set");
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        String client = getClientFromRequest(authentication);
        //只有pc端需要校验密码
        if (StringUtils.hasText(client)) {
            if (authentication.getCredentials() == null) {
                logger.debug("Authentication failed: no credentials provided");

                throw new BadCredentialsException(messages.getMessage(
                        "AbstractUserDetailsAuthenticationProvider.badCredentials",
                        "Bad credentials"));
            }

            String presentedPassword = authentication.getCredentials().toString();

            if (!passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
                logger.debug("Authentication failed: password does not match stored value");

                throw new BadCredentialsException(messages.getMessage(
                        "AbstractUserDetailsAuthenticationProvider.badCredentials",
                        "Bad credentials"));
            }
        }
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        prepareTimingAttackProtection();
        try {
            String client = getClientFromRequest(authentication);
            UserDetails loadedUser = null;
            for (BaseUserDetailsService userDetailsService : userDetailsServices) {
                if (userDetailsService.supports(ClientEnum.valueOf(client))) {
                    loadedUser = userDetailsService.loadUserByUsername(username);
                    break;
                }
            }
            if (loadedUser == null) {
                throw new InternalAuthenticationServiceException(
                        "UserDetailsService returned null, which is an interface contract violation");
            }
            return loadedUser;
        } catch (UsernameNotFoundException ex) {
            mitigateAgainstTimingAttack(authentication);
            throw ex;
        } catch (InternalAuthenticationServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex);
        }
    }

    private String getClientFromRequest(UsernamePasswordAuthenticationToken authentication) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null == requestAttributes) {
            throw new CommonException(CommonCodeEnum.SERVER_ERROR);
        }
        HttpServletRequest request = requestAttributes.getRequest();
        String client = request.getHeader(HEADER_CLIENT);
        if (!StringUtils.hasText(client)) {
            if (authentication.getDetails() instanceof Map) {
                //password模式下，会在ResourceOwnerPasswordTokenGranter#getOAuth2Authentication方法中把参数放入一个map，设置到details字段中
                Map<String, String> details = (Map<String, String>) authentication.getDetails();
                client = details.get(HEADER_CLIENT);
            } else if (authentication.getDetails() instanceof CustomWebAuthenticationDetails) {
                //authorization_code模式下，走的是spring security默认的认证流程，details字段默认是一个WebAuthenticationDetails对象
                //自定义配置UsernamePasswordAuthenticationFilter中的AuthenticationDetailsSource，可以修改details字段
                CustomWebAuthenticationDetails details = (CustomWebAuthenticationDetails) authentication.getDetails();
                client = details.getClient();
            }
        }
        if (!StringUtils.hasText(client)) {
//            throw new CommonException(BizCodeEnum.HEADER_CLIENT_NOT_SUPPORT);
            return "PC";
        }
        return client;
    }

    @Override
    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails user) {
        boolean upgradeEncoding = this.userDetailsPasswordService != null
                && this.passwordEncoder.upgradeEncoding(user.getPassword());
        if (upgradeEncoding) {
            String presentedPassword = authentication.getCredentials().toString();
            String newPassword = this.passwordEncoder.encode(presentedPassword);
            user = this.userDetailsPasswordService.updatePassword(user, newPassword);
        }
        return super.createSuccessAuthentication(principal, authentication, user);
    }

    private void prepareTimingAttackProtection() {
        if (this.userNotFoundEncodedPassword == null) {
            this.userNotFoundEncodedPassword = this.passwordEncoder.encode(USER_NOT_FOUND_PASSWORD);
        }
    }

    private void mitigateAgainstTimingAttack(UsernamePasswordAuthenticationToken authentication) {
        if (authentication.getCredentials() != null) {
            String presentedPassword = authentication.getCredentials().toString();
            this.passwordEncoder.matches(presentedPassword, this.userNotFoundEncodedPassword);
        }
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        Assert.notNull(passwordEncoder, "passwordEncoder cannot be null");
        this.passwordEncoder = passwordEncoder;
        this.userNotFoundEncodedPassword = null;
    }

    public List<BaseUserDetailsService> getUserDetailsServices() {
        return userDetailsServices;
    }

    public void setUserDetailsServices(List<BaseUserDetailsService> userDetailsServices) {
        this.userDetailsServices = userDetailsServices;
    }

    public UserDetailsPasswordService getUserDetailsPasswordService() {
        return userDetailsPasswordService;
    }

    public void setUserDetailsPasswordService(UserDetailsPasswordService userDetailsPasswordService) {
        this.userDetailsPasswordService = userDetailsPasswordService;
    }
}
