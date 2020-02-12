# SpringCloud作为的新的项目架构

### it‘s cool， right？

## 项目结构

dount-cloud-learning
├── [dount-eureka-server](https://github.com/MichaelWongK/dount-cloud-learn/tree/master/dount-eureka-server) -- 带登录认证的eureka注册中心及多节点互为主备(replica1：8002，replica2：8003)
├── [dount-eureka-client ](https://github.com/MichaelWongK/dount-cloud-learn/tree/master/dount-eureka-client)-- eureka客户端，验证在eureka-server端的注册
├── [dount-user-service](https://github.com/MichaelWongK/dount-cloud-learn/tree/master/dount-user-service) -- 提供User对象CRUD接口的服务
├── [dount-ribbon](https://github.com/MichaelWongK/dount-cloud-learn/tree/master/dount-ribbon) -- ribbon服务调用测试服务,调用多个user-servic验证负载均衡（随机 or 轮循）
├── [dount-hystrix-service](https://github.com/MichaelWongK/dount-cloud-learn/tree/master/dount-hystrix-service) -- hystrix服务调采用ribbon的方式调用user-service服务接口，包含熔断相关操作
├── [dount-hystrix-turbine](https://github.com/MichaelWongK/dount-cloud-learn/tree/master/dount-hystrix-turbine) -- 聚合收集hystrix实例监控信息的服务
├── [dount-hystrix-dashboard](https://github.com/MichaelWongK/dount-cloud-learn/tree/master/dount-hystrix-dashboard) -- hystrix实例监控信息的仪表盘，单Hystrix实例监控及多Hystrix集群状态监控
├── [dount-feign-service](https://github.com/MichaelWongK/dount-cloud-learn/tree/master/dount-feign-service) -- feign服务调用user-service服务接口实现了负载均衡的服务调用方式及熔断
├── [dount-api-gateway](https://github.com/MichaelWongK/dount-cloud-learn/tree/master/dount-api-gateway) -- gateway作为网关的测试服务
├── [dount-nacos/ ](https://github.com/MichaelWongK/dount-cloud-learn/tree/master/dount-nacos)-- 用于演示Alibaba Nacos作为配置中心和注册中心的nacos客户端
├── [dount-nacos/nacos-config-client ](https://github.com/MichaelWongK/dount-cloud-learn/tree/master/dount-nacos/nacos-config-client)-- 用于演示nacos作为配置中心的nacos客户端
├── [dount-nacos/nacos-user-service](https://github.com/MichaelWongK/dount-cloud-learn/tree/master/dount-nacos/nacos-user-service) -- 注册到nacos的提供User对象CRUD接口的服务
├── [dount-nacos/nacos-ribbon-service](https://github.com/MichaelWongK/dount-cloud-learn/tree/master/dount-nacos/nacos-ribbon-service) -- 注册到nacos的ribbon服务调用测试服务

## 参考项目

Spring-Cloud GitHub实战项目项目：[电商商城的Cloud项目](https://github.com/paascloud/paascloud-master)