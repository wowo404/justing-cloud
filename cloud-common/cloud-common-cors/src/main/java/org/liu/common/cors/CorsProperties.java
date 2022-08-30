package org.liu.common.cors;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lzs
 * @Date 2022/8/5 15:50
 **/
@Data
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "base.cors")
public class CorsProperties {
    private List<String> allowedOrigins = new ArrayList<>();
}
