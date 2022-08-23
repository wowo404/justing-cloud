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
    - fixed at 20220726，这个疑问在引入oauth2后就不存在了，除网关服务和auth服务外，其他微服务每一个都是资源服务，参考oauth2资源服务
6. 接入分布式事务框架seata
7. 引入链路追踪
8. 引入spring-cloud-starter-netflix-hystrix-dashboard
9. 完成gateway

# 思考
1. zuul中需要为每一个微服务添加fallback provider吗？？
2. auth服务使用的是oauth2，在joolun商城项目中，每一个微服务就是一个client，这是一种错误的架构，把resource端和client端搞混淆了
3. po类是否移动到feign-pojo模块中供外部调用与内部使用共用？
    - 答：共用，在实际开发中，有大量的请求或者响应类（特别是响应类）要继承po类，如果不共用的话，响应字段要在copy一遍，增加工作量，当代码修改时，也容易遗漏修改
4. auth服务查询用户及权限信息，是在auth服务配置多数据源直接查询还是feign远程调用？
    - 答：feign远程调用
5. 最近一直在试图把pc端认证和微信端认证一起整合到oauth2中，并且要支持oauth2的4种模式，目前password、implicit、client_credentials是没有问题，
authorization_code模式下无法收到自定义的参数，通过一通自定义操作后，参数是可以传递过去了，但是仍然无法解决“无法跳回/oauth/authorize请求”问题，
因为有个前置zuul服务导致session不一致，这个问题和多个认证实现无关，即使保留一个pc端认证，也有这个问题，如何解决？
    - 答1：把auth服务和zuul合并，此方案的缺点：网关服务和认证服务合并，不符合一个微服务只负责一个单一职责的原则

# 警告
- 在业务系统中不要使用user，user这个概念太过宽泛，微信用户，后台用户，供应商用户，商户用户，操作系统用户？？？到底指的是哪一类呢
- 如果是在一个可以复用的框架中，要抽象一个用户的概念，可以使用user，比如spring security这种通用的安全框架
- po类中不要使用@TableField注解，因为此注解依赖了mybatis包中的类

# 系统模块结构
- cloud-activity：7417：活动服务：含优惠券。错误码：以10开头
- cloud-admin：7421：管理后台权限服务。错误码：以11开头
- cloud-auth：7474：认证及授权服务。错误码：以12开头
- cloud-common：公共聚合模块。错误码：以13开头
- cloud-config-server：7411：配置中心。错误码：以14开头
- cloud-coupon：7419：优惠券服务。错误码：以15开头
- cloud-eureka：7410：服务发现。错误码：以16开头
- cloud-flow：7416：流程审批服务。错误码：以17开头
- cloud-gateway：10086：以spring-cloud-starter-gateway为框架搭建的网关。错误码：以18开头
- cloud-order：7412：订单服务：含支付服务。错误码：以19开头
- cloud-product：7418：产品服务：spu，sku。错误码：以20开头
- cloud-public：7414：公共基础服务：诸如全局公共配置，数据字典，消息，短信，文件上传下载等。错误码：以21开头
- cloud-search：7420：搜索服务。错误码：以22开头
- cloud-storage：7415：库存服务，~~~~此服务和产品服务是不是重合了，有待进一步了解~~~~。错误码：以23开头
- cloud-wx：7413：用户服务，通常是微信用户或者web端的用户，跟管理后台的用户区分开来。错误码：以24开头
- cloud-zuul：10086：以zuul为框架搭建的网关。错误码：以25开头
