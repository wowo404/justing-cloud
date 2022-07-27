package org.liu.common.service.datascope;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lzs
 * @Date 2022/7/27 14:04
 **/
@Data
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "base.datascope")
public class DataScopeProperties {
    private List<String> mapperIds = new ArrayList<>();
}
