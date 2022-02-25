package org.liu.publicbase.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试用或者给docker部署时使用
 * @Author lzs
 * @Date 2022/2/21 16:39
 **/
@RestController
public class HelloController {

    @RequestMapping("ok")
    public String ok() {
        return "ok";
    }

}
