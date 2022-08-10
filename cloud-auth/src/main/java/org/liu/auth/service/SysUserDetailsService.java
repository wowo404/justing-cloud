package org.liu.auth.service;

import lombok.RequiredArgsConstructor;
import org.justing.commons.exception.CommonException;
import org.justing.commons.model.Response;
import org.liu.admin.feign.client.OperatorClient;
import org.liu.admin.feign.pojo.po.Menu;
import org.liu.admin.feign.pojo.po.Role;
import org.liu.admin.feign.pojo.resp.OperatorDetailResp;
import org.liu.common.core.enums.ClientEnum;
import org.liu.common.core.enums.DataScopeEnum;
import org.liu.common.security.base.pojo.BaseUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import static org.liu.auth.exception.BizCodeEnum.ERROR_GET_OPERATOR_DETAIL;
import static org.liu.auth.exception.BizCodeEnum.ROLE_MUST_HAVE_DATA_SCOPE;

/**
 * @Author lzs
 * @Date 2022/7/13 17:51
 **/
@RequiredArgsConstructor
@Component
public class SysUserDetailsService implements BaseUserDetailsService {

    private final OperatorClient operatorClient;

    @Override
    public Boolean supports(ClientEnum client) {
        return ClientEnum.PC.equals(client);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Response<OperatorDetailResp> response = operatorClient.queryByUsername(username);
        if (!response.isOk()) {
            throw new CommonException(ERROR_GET_OPERATOR_DETAIL);
        }
        OperatorDetailResp operator = response.getData();
        boolean credentialsNonExpired = operator.getCredentialsExpireTime().after(new Date());
        Optional<Role> minRole = operator.getRoles().stream().min(Comparator.comparingInt(Role::getDataScope));
        if (!minRole.isPresent()) {
            throw new CommonException(ROLE_MUST_HAVE_DATA_SCOPE);
        }
        //菜单和角色
        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(operator.getMenus().stream().map(Menu::getUrl).distinct().toArray(String[]::new));
        authorityList.addAll(AuthorityUtils.createAuthorityList(operator.getRoles().stream().map(role -> "ROLE_" + role.getCode()).distinct().toArray(String[]::new)));

        BaseUser baseUser = new BaseUser(operator.getUsername(), operator.getPassword(), true, true,
                credentialsNonExpired, true, authorityList);
        baseUser.setId(operator.getId());
        baseUser.setTenantId(operator.getTenantId());
        baseUser.setDisplayName(operator.getOperatorName());
        baseUser.setRoleIds(operator.getRoles().stream().map(Role::getId).collect(Collectors.toList()));
        baseUser.setDataScope(DataScopeEnum.parseByCode(minRole.get().getDataScope()));
        if (DataScopeEnum.CUSTOM.equals(baseUser.getDataScope())) {
            baseUser.setDeptIds(toLongList(minRole.get().getDeptIds()));
        } else {
            baseUser.setDeptIds(Collections.singletonList(operator.getDeptId()));
        }
        return baseUser;
    }

    private List<Long> toLongList(String deptIds) {
        String[] split = deptIds.split(",");
        List<Long> list = new ArrayList<>(split.length);
        for (String deptId : split) {
            list.add(Long.parseLong(deptId));
        }
        return list;
    }

}
