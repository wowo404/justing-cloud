package org.liu.common.service.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import org.liu.common.service.tenant.BaseTenantHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author lzs
 * @Date 2022/7/26 16:59
 **/
@MapperScan("org.liu.**.mapper")
@Configuration
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(BaseTenantHandler baseTenantHandler) {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //租户
        interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(baseTenantHandler));
        //乐观锁
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        //分页
        PaginationInnerInterceptor pagination = new PaginationInnerInterceptor();
        pagination.setDbType(DbType.MYSQL);//对于单一数据库类型来说,都建议配置该值,避免每次分页都去抓取数据库类型
        interceptor.addInnerInterceptor(pagination);
        return interceptor;
    }

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setUseDeprecatedExecutor(false);
    }

}
