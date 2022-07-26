package org.liu.common.service.tenant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lzs
 * @Date 2022/7/26 17:33
 **/
@Data
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "base.tenant")
public class TenantProperties {
    private List<String> tables = new ArrayList<>();
}
