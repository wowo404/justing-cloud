package org.liu.common.service.tenant;

import lombok.experimental.UtilityClass;

/**
 * @author 租户工具类
 */
@UtilityClass
public class TenantContextHolder {

    private final ThreadLocal<Long> THREAD_LOCAL_TENANT = new InheritableThreadLocal<>();

    /**
     * TTL 设置租户ID
     *
     * @param tenantId
     */
    public void setTenantId(Long tenantId) {
        THREAD_LOCAL_TENANT.set(tenantId);
    }

    /**
     * 获取TTL中的租户ID
     *
     * @return
     */
    public Long getTenantId() {
        return THREAD_LOCAL_TENANT.get();
    }

    public void clear() {
        THREAD_LOCAL_TENANT.remove();
    }
}
