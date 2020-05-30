package org.liu.user.feign.client.fallback;

import org.justing.commons.model.Response;
import org.liu.user.feign.client.UserClient;
import org.liu.user.feign.pojo.UserResp;
import org.springframework.stereotype.Component;

@Component
public class UserClientFallback implements UserClient {
    @Override
    public Response<UserResp> getById(Long userId) {
        return Response.serverError();
    }
}
