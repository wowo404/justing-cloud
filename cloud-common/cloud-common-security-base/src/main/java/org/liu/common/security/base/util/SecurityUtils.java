package org.liu.common.security.base.util;

import org.liu.common.core.constants.CommonConstants;
import org.liu.common.security.base.pojo.BaseUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

/**
 * @Author lzs
 * @Date 2022/7/27 14:40
 **/
public class SecurityUtils {

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static BaseUser getBaseUser() {
        return (BaseUser) getAuthentication();
    }

    public static String getUsername() {
        return getAuthentication().getName();
    }

    public static Long getId() {
        return getBaseUser().getId();
    }

    public static Long getTenantId() {
        return getBaseUser().getTenantId();
    }

    public static String getDisplayName() {
        return getBaseUser().getDisplayName();
    }

    public static List<Long> getDeptIds() {
        return getBaseUser().getDeptIds();
    }

    public static boolean isSuper() {
        return isSuperTenant() || isSuperOperator() || isSuperRole();
    }

    public static boolean isSuperTenant() {
        return getTenantId().equals(CommonConstants.SUPER_TENANT);
    }

    public static boolean isSuperOperator() {
        return getTenantId().equals(CommonConstants.SUPER_OPERATOR);
    }

    public static boolean isSuperRole() {
        return getTenantId().equals(CommonConstants.SUPER_ROLE);
    }
}
