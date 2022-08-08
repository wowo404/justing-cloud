package org.liu.zuul.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lzs
 * @Date 2022/7/30 14:49
 **/
@Data
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "base.auth.ignore")
public class BaseAuthIgnoreProperties {
    private List<String> urls = new ArrayList<>();
}
