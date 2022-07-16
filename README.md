# 遗留的问题有
 1. 很多地方还在用httpComponent或者jdk的方式，而没有用okhttp，比如eureka client
 2. ConfigServicePropertySourceLocator中有用到RestTemplate，但用的jdk的方式，可以跟踪到locate方法看，手动重新注册了ConfigServicePropertySourceLocator，但没有生效
      ConfigServiceBootstrapConfiguration中的自动注册仍然会生效，如果要使自定义的生效，参考https://www.jianshu.com/p/769939eb1fb3
 3. http方式远程同步请求可以使用restTemplate，异步请求可以使用asyncRestTemplate，但现在使用了feign，如何做异步，在方法上加@Async注解？

# TODO
1. 分布式事务：XA协议（两阶段提交（2PC）、三阶段提交（3PC））、saga、TCC、本地消息表、本地消息表、尽最大努力通知
2. zuul流量控制
3. 把以下服务完善：
    - 用户服务：
    - 资金服务：
    - 产品服务：spu，sku
    - 订单服务：
    - 流程审批服务：
    - 活动服务：
4. 编写默认的统一的fallback，先要读懂FeignAutoConfiguration，参考商城的base-common-security，此模块下有写
5. feign接口如何跳过安全权限校验，参考商城的base模块
    - url上加统一前缀或参数，在过滤器中跳过？
    - 添加head参数，在过滤器中拿到该参数，等于某个值时跳过？
6. 接入分布式事务框架seata
7. 引入链路追踪
8. 引入spring-cloud-starter-netflix-hystrix-dashboard
9. 完成gateway

# 思考
1. zuul中需要为每一个微服务添加fallback provider吗？？
2. auth服务使用的是oauth2，在joolun商城项目中，每一个微服务就是一个client，这是一种错误的架构，把resource端和client端搞混淆了

# 系统模块结构
- cloud-activity：7417：活动服务：含优惠券
- cloud-auth：7474：认证及授权服务
- cloud-common：公共聚合模块
- cloud-config-server：7411：配置中心
- cloud-coupon：7419：优惠券服务
- cloud-eureka：7410：服务发现
- cloud-flow：7416：流程审批服务
- cloud-gateway：10086：以spring-cloud-starter-gateway为框架搭建的网关
- cloud-order：7412：订单服务：含支付服务
- cloud-product：7418：产品服务：spu，sku
- cloud-public：7414：公共基础服务：诸如全局公共配置，数据字典，消息，短信，文件上传下载等
- cloud-search：7420：搜索服务
- cloud-storage：7415：库存服务，~~~~此服务和产品服务是不是重合了，有待进一步了解~~~~
- cloud-user：7413：用户服务
- cloud-zuul：10086：以zuul为框架搭建的网关
