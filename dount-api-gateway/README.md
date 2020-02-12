# Spring Cloud Gateway

Spring Cloud Gateway 为 SpringBoot 应用提供了API网关支持，具有强大的智能路由与过滤器功能。

Gateway是在Spring生态系统之上构建的API网关服务，基于Spring 5，Spring Boot 2和 Project Reactor等技术。Gateway旨在提供一种简单而有效的方式来对API进行路由，以及提供一些强大的过滤器功能， 例如：熔断、限流、重试等。

Spring Cloud Gateway 具有如下特性：

- 基于Spring Framework 5, Project Reactor 和 Spring Boot 2.0 进行构建；
- 动态路由：能够匹配任何请求属性；
- 可以对路由指定 Predicate（断言）和 Filter（过滤器）；
- 集成Hystrix的断路器功能；
- 集成 Spring Cloud 服务发现功能；
- 易于编写的 Predicate（断言）和 Filter（过滤器）；
- 请求限流功能；
- 支持路径重写。

## 相关概念

- Route（路由）：路由是构建网关的基本模块，它由ID，目标URI，一系列的断言和过滤器组成，如果断言为true则匹配该路由；
- Predicate（断言）：指的是Java 8 的 Function Predicate。 输入类型是Spring框架中的ServerWebExchange。 这使开发人员可以匹配HTTP请求中的所有内容，例如请求头或请求参数。如果请求与断言相匹配，则进行路由；
- Filter（过滤器）：指的是Spring框架中GatewayFilter的实例，使用过滤器，可以在请求被路由前后对请求进行修改。

## 两种不同的配置路由方式

> Gateway 提供了两种不同的方式用于配置路由，一种是通过yml文件来配置，另一种是通过Java Bean来配置。

## Route Filter 的使用

> 路由过滤器可用于修改进入的HTTP请求和返回的HTTP响应，路由过滤器只能指定路由进行使用。Spring Cloud Gateway 内置了多种路由过滤器，他们都由GatewayFilter的工厂类来产生。

## 结合注册中心使用

### 使用动态路由

在微服务中，如商品服务，肯定是集群部署的。**那Gateway怎么路由到多个商品服务呢？**

> 还有服务注册中心里面**往往注册了很多服务**，如果每个服务都需要单独配置的话，这将是一份很枯燥的工作。**Gateway 提供了一种默认转发的能力**，只要将Gateway 注册到服务中心，Gateway 默认就会代理服务中心的所有服务。

### 使用过滤器

> 在结合注册中心使用过滤器的时候，我们需要注意的是uri的协议为`lb`，这样才能启用Gateway的负载均衡功能。

## 使用到的模块

```
dount-cloud-learning
├── dount-eureka-server -- eureka注册中心
├── dount-user-service -- 提供User对象CRUD接口的服务
└── dount-api-gateway -- gateway作为网关的测试服务
```