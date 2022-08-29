package org.liu.auth.endpoint;


import lombok.RequiredArgsConstructor;
import org.justing.commons.model.Response;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 注销
 */
@RequiredArgsConstructor
@FrameworkEndpoint
public class RevokeTokenEndpoint {

    @Qualifier("consumerTokenServices")
    private final ConsumerTokenServices consumerTokenServices;

    @RequestMapping(method = RequestMethod.DELETE, value = "/oauth/token")
    @ResponseBody
    public Response<Void> revokeToken(String access_token) {
        if (consumerTokenServices.revokeToken(access_token)) {
            return Response.ok();
        } else {
            return Response.error();
        }
    }
}
