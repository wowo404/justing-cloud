package org.liu.admin.feign.client.fallback;

import org.justing.commons.model.Response;
import org.liu.admin.feign.client.OperatorClient;
import org.liu.admin.feign.pojo.resp.OperatorDetailResp;
import org.springframework.stereotype.Component;

@Component
public class OperatorClientFallback implements OperatorClient {
    @Override
    public Response<OperatorDetailResp> queryByUsername(String username) {
        return Response.serverError();
    }
}
