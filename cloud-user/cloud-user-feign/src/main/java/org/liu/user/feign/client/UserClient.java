package org.liu.user.feign.client;

import org.justing.commons.model.Response;
import org.liu.user.feign.client.config.FeignConfig;
import org.liu.user.feign.client.fallback.UserClientFallback;
import org.liu.user.feign.pojo.UserResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "cloud-user", fallback = UserClientFallback.class, configuration = FeignConfig.class)
public interface UserClient {

    @GetMapping("user/{userId}")
    Response<UserResp> getById(@PathVariable("userId") Long userId);

}
