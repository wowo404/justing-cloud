# 遗留的问题有
 1.很多地方还在用httpComponent或者jdk的方式，而没有用okhttp，比如eureka client
 2.ConfigServicePropertySourceLocator中有用到RestTemplate，但用的jdk的方式，可以跟踪到locate方法看，手动重新注册了ConfigServicePropertySourceLocator，但没有生效
   ConfigServiceBootstrapConfiguration中的自动注册仍然会生效，如果要使自定义的生效，参考https://www.jianshu.com/p/769939eb1fb3
 3.http方式远程同步请求可以使用restTemplate，异步请求可以使用asyncRestTemplate，但现在使用了feign，如何做异步，在方法上加@Async注解？
 
# TODO
1.分布式事务：XA协议（两阶段提交（2PC）、三阶段提交（3PC））、saga、TCC、本地消息表、本地消息表、尽最大努力通知
2.zuul流量控制
3.把以下服务完善：
 - 用户服务：
 - 资金服务：
 - 产品服务：spu，sku
 - 订单服务：
 - 流程审批服务：
 - 优惠活动服务：

#思考
1.zuul中需要为每一个微服务添加fallback provider吗？？