package org.liu.storage.feign.client.fallback;

import org.justing.commons.model.Response;
import org.liu.storage.feign.client.StorageClient;
import org.liu.storage.feign.pojo.OperateStorageReq;

public class StorageClientFallback implements StorageClient {
    @Override
    public Response<Void> operate(OperateStorageReq req) {
        return Response.serverError();
    }
}
