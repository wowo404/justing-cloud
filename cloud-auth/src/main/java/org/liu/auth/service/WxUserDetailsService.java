package org.liu.auth.service;

import lombok.RequiredArgsConstructor;
import org.justing.commons.exception.CommonException;
import org.justing.commons.model.Response;
import org.liu.common.core.enums.ClientEnum;
import org.liu.common.core.enums.DataScopeEnum;
import org.liu.common.security.base.pojo.BaseUser;
import org.liu.wx.feign.client.WxUserClient;
import org.liu.wx.feign.pojo.po.WxUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.liu.auth.exception.BizCodeEnum.ERROR_GET_OPERATOR_DETAIL;
import static org.liu.common.core.constants.CommonConstants.SUPER_SYS_ROLE_NAME;

/**
 * @Author lzs
 * @Date 2022/8/1 17:56
 **/
@RequiredArgsConstructor
//@Component
public class WxUserDetailsService implements BaseUserDetailsService {

    private final WxUserClient wxUserClient;

    @Override
    public Boolean supports(ClientEnum client) {
        return ClientEnum.MINIAPP.equals(client);
    }

    /**
     * 通过jsCode查询用户信息
     * @param username 即jsCode
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Response<WxUser> response = wxUserClient.queryByJsCode(username);
        if (!response.isOk()) {
            throw new CommonException(ERROR_GET_OPERATOR_DETAIL);
        }
        WxUser wxUser = response.getData();
        //菜单和角色
        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList("ROLE_" + SUPER_SYS_ROLE_NAME);

        BaseUser baseUser = new BaseUser(wxUser.getOpenId(), null, true, true,
                true, true, authorityList);
        baseUser.setId(wxUser.getId());
        baseUser.setTenantId(wxUser.getTenantId());
        baseUser.setDisplayName(wxUser.getNickName());
        baseUser.setRoleIds(null);
        baseUser.setDataScope(DataScopeEnum.ALL);
        baseUser.setDeptIds(null);
        return baseUser;
    }
}
