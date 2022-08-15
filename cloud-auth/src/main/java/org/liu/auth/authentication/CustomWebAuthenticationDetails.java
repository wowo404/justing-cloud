package org.liu.auth.authentication;

import lombok.Getter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author lzs
 * @Date 2022/8/15 16:17
 **/
@Getter
public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {

    private final String client;
    private final Long tenantId;

    public CustomWebAuthenticationDetails(HttpServletRequest request, String client, Long tenantId) {
        super(request);
        this.client = client;
        this.tenantId = tenantId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CustomWebAuthenticationDetails) {
            CustomWebAuthenticationDetails details = (CustomWebAuthenticationDetails) obj;
            if (null != client && null == details.getClient()) {
                return false;
            }
            if (null == client && null != details.getClient()) {
                return false;
            }
            if (null != client && !client.equals(details.getClient())) {
                return false;
            }

            if (null != tenantId && null == details.getTenantId()) {
                return false;
            }
            if (null == tenantId && null != details.getTenantId()) {
                return false;
            }
            if (null != tenantId && tenantId.equals(details.getTenantId())) {
                return false;
            }
        } else {
            return false;
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        int code = super.hashCode();
        if (null != client) {
            code = code * (this.client.hashCode() % 7);
        }
        if (null != tenantId) {
            code = code * (this.tenantId.hashCode() % 7);
        }
        return code;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(": ");
        sb.append("client: ").append(this.getClient()).append("; ");
        sb.append("tenantId: ").append(this.getTenantId());

        return sb.toString();
    }
}
