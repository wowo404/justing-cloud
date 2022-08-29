package org.liu.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 此controller的作用是给client端提供security.oauth2.resource.user-info-uri配置
 *
 * @Author lzs
 * @Date 2022/8/25 13:58
 **/
@RestController
public class UserController {

    @GetMapping("user")
    public Principal getUser(Principal user){
        return user;
    }

}
