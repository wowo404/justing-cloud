package org.liu.common.security.base.pojo;

import lombok.Getter;
import lombok.Setter;
import org.liu.common.core.enums.ClientEnum;
import org.liu.common.core.enums.DataScopeEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

/**
 * @Author lzs
 * @Date 2022/7/28 11:23
 **/
@Getter
@Setter
public class BaseUser extends User {
    /**
     * client=PC时为sysUser.id，client=MINIAPP时为wxUser.id
     */
    private Long id;
    /**
     * 租户ID
     */
    private Long tenantId;
    /**
     * 客户端
     */
    private ClientEnum client;
    /**
     * client=PC时为sysUser.operatorName，client=MINIAPP时为wxUser.nickName
     */
    private String displayName;
    /**
     * 角色ID列表
     */
    private List<Long> roleIds;
    /**
     * 数据权限范围
     */
    private DataScopeEnum dataScope;
    /**
     * dataScope=0时，可能无数据，dataScope!=4时，只有一条数据，来自sys_operator.dept_id，dataScope=4时，来自sys_role.dept_ids
     */
    private List<Long> deptIds;

    public BaseUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
