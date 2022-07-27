package org.liu.common.service.datascope;

import com.baomidou.mybatisplus.extension.handlers.AbstractSqlParserHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;

/**
 * @Author lzs
 * @Date 2022/7/27 9:06
 **/
public class DataScopeInterceptor extends AbstractSqlParserHandler implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        return null;
    }
}
