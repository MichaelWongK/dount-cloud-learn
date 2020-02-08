# Spring Cloud Alibaba：Nacos 作为注册中心和配置中心使用

Spring Cloud Alibaba 致力于提供微服务开发的一站式解决方案，Nacos 作为其核心组件之一，可以作为注册中心和配置中心使用，[关于Nacos的官方介绍可点击我查看](https://nacos.io/zh-cn/docs/what-is-nacos.html)。

## Nacos与eureka注册中心对比

| 对比项目\注册中心  | Spring Cloud Nacos                                           | Spring Cloud Eureka                                          |
| ------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| CAP模型            | 支持AP和CP模型                                               | AP模型                                                       |
| 客户端更新服务信息 | 使用注册+DNS-f+健康检查模式。 DNS-F客户端使用监听模式push/pull拉取更新信息 | 客户端定时轮询服务端获取其他服务ip信息并对比，相比之下服务端压力较大、延迟较大 |
| 伸缩性             | 使用Raft选举算法性能、可用性、容错性均比较好，新加入节点无需与所有节点互相广播同步信息 | 由于使用广播同步信息，集群超过1000台机器后对eureka集群压力很大 |
| 健康检查模式/方式  | 支持服务端/客户端/关闭检查模式，检查方式有tcp、http、sql。支持自己构建健康检查器 | 客户端向服务端发送http心跳                                   |
| 负载均衡           | 支持                                                         | 支持                                                         |
| 手动上下线服务方式 | 通过控制台页面和API                                          | 通过调用API                                                  |
| 跨中心同步         | 支持                                                         | 不支持                                                       |
| k8s集成            | 支持                                                         | 不支持                                                       |
| 分组               | Nacos可用根据业务和环境进行分组管理                          | 不支持                                                       |
| 权重               | Nacos默认提供权重设置功能，调整承载流量压力                  | 不支持                                                       |
| 厂商               | 阿里巴巴                                                     | Netflix                                                      |

## 使用Nacos代替Eureka优势：

 **相比与Eureka：**
 (1)Nacos具备服务优雅上下线和流量管理（API+后台管理页面），而Eureka的后台页面仅供展示，需要使用api操作上下线且不具备流量管理功能。
 (2)从部署来看，Nacos整合了注册中心、配置中心功能，把原来两套集群整合成一套，简化了部署维护
 (3)从长远来看，Eureka开源工作已停止，后续不再有更新和维护，而Nacos在以后的版本会支持SpringCLoud+Kubernetes的组合，填补 2 者的鸿沟，在两套体系下可以采用同一套服务发现和配置管理的解决方案，这将大大的简化使用和维护的成本。同时来说,Nacos 计划实现 Service Mesh，是未来微服务的趋势
 (4)从伸缩性和扩展性来看Nacos支持跨注册中心同步，而Eureka不支持，且在伸缩扩容方面，Nacos比Eureka更优（nacos支持大数量级的集群）。
 (5)Nacos具有分组隔离功能，一套Nacos集群可以支撑多项目、多环境。

## 使用Nacos作为注册中心

### 安装并运行Nacos

- Nacos的服务端需要单独下载部署使用，所以项目中使用Nacos的方式都是作为Nacos的client端来使用的，

  下载地址：[github.com/alibaba/nac…](https://github.com/alibaba/nacos/releases)

- 配置`JAVA_HOME`环境变量，不配置会导致无法运行Nacos；

```
JAVA_HOME=D:\developer\env\Java\jdk1.8.0_91
```

- 解压安装包，直接运行`bin`目录下的`startup.cmd`；
- 运行成功后，访问`http://localhost:8848/nacos`可以查看Nacos的主页，默认账号密码都是nacos。

### 创建应用注册到Nacos

> 我们通过改造consul-user-service和consul-ribbon-service来演示下服务注册与发现的功能，主要是将应用原来的Consul注册中心支持改为Nacos注册中心支持。

### 运行两个nacos-user-service和一个nacos-ribbon-service，在Nacos页面上可以看到如下信息：

![](D:\workspace\git\dount-cloud-learn\dount-nacos\nacos-config-client\src\main\resources\file\nacos app list.jpg)

## 使用Nacos作为配置中心

> 我们通过创建nacos-config-client模块，在Nacos页面中添加配置信息可以使用配置管理的功能。

## Nacos的动态刷新配置

我们只要修改下Nacos中的配置信息，再次调用查看配置的接口，就会发现配置已经刷新，Nacos和Consul一样都支持动态刷新配置。当我们在Nacos页面上修改配置并发布后，应用会刷新配置并打印如下信息

```
2020-02-09 00:34:02.340  INFO 2476 --- [-localhost_8848] trationDelegate$BeanPostProcessorChecker : Bean 'org.springframework.cloud.autoconfigure.ConfigurationPropertiesRebinderAutoConfiguration' of type [org.springframework.cloud.autoconfigure.ConfigurationPropertiesRebinderAutoConfiguration$$EnhancerBySpringCGLIB$$d342a728] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
2020-02-09 00:34:03.209  INFO 2476 --- [-localhost_8848] c.a.c.n.c.NacosPropertySourceBuilder     : Loading nacos data, dataId: 'nacos-config-client-dev.yaml', group: 'DEFAULT_GROUP'
2020-02-09 00:34:03.210  INFO 2476 --- [-localhost_8848] b.c.PropertySourceBootstrapConfiguration : Located property source: CompositePropertySource {name='NACOS', propertySources=[NacosPropertySource {name='nacos-config-client-dev.yaml'}, NacosPropertySource {name='nacos-config-client.yaml'}]}
2020-02-09 00:34:03.211  INFO 2476 --- [-localhost_8848] o.s.boot.SpringApplication               : The following profiles are active: dev
2020-02-09 00:34:03.220  INFO 2476 --- [-localhost_8848] o.s.boot.SpringApplication               : Started application in 2.036 seconds (JVM running for 512.114)
2020-02-09 00:34:03.234  INFO 2476 --- [-localhost_8848] o.s.c.e.event.RefreshEventListener       : Refresh keys changed: [config.info]

```

## 使用到的模块

```
springcloud-learning
├── nacos-config-client -- 用于演示nacos作为配置中心的nacos客户端
├── nacos-user-service -- 注册到nacos的提供User对象CRUD接口的服务
└── nacos-ribbon-service -- 注册到nacos的ribbon服务调用测试服务
```

## 参考资料

Spring Cloud Alibaba 官方文档：[github.com/alibaba/spr…](https://github.com/alibaba/spring-cloud-alibaba/wiki)

## 