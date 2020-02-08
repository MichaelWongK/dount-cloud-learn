# Spring Cloud OpenFeign：基于Ribbon和Hystrix的声明式服务调用

 	Spring Cloud OpenFeign 是声明式的服务调用工具，它整合了Ribbon和Hystrix，拥有负载均衡和服务容错功能。

## Feign简介

Feign是声明式的服务调用工具，我们只需创建一个接口并用注解的方式来配置它，就可以实现对某个服务接口的调用，简化了直接使用RestTemplate来调用服务接口的开发量。Feign具备可插拔的注解支持，同时支持Feign注解、JAX-RS注解及SpringMvc注解。当使用Feign时，Spring Cloud集成了Ribbon和Eureka以提供负载均衡的服务调用及基于Hystrix的服务容错保护功能。

### 在启动类上添加@EnableFeignClients注解来启用Feign的客户端功能

### 添加UserService接口完成对user-service服务的接口绑定

> 我们通过@FeignClient注解实现了一个Feign客户端，其中的value为user-service表示这是对user-service服务的接口调用客户端。我们可以回想下user-service中的UserController，只需将其改为接口，保留原来的SpringMvc注释即可。

## Feign中的服务降级

> Feign中的服务降级使用起来非常方便，只需要为Feign客户端定义的接口添加一个服务降级处理的实现类即可，下面我们为UserService接口添加一个服务降级实现类。

### 添加服务降级实现类UserFallbackService

> 需要注意的是它实现了UserService接口，并且对接口中的每个实现方法进行了服务降级逻辑的实现。

### 修改UserService接口，设置服务降级处理类为UserFallbackService

> 修改@FeignClient注解中的参数，设置fallback为UserFallbackService.class即可。

```
@FeignClient(value = "user-service",fallback = UserFallbackService.class)
public interface UserService {
}
复制代码
```

### 修改application.yml，开启Hystrix功能

```
feign:
  hystrix:
    enabled: true #在Feign中开启Hystrix
```

## 日志打印功能

> Feign提供了日志打印功能，我们可以通过配置来调整日志级别，从而了解Feign中Http请求的细节。

### 日志级别

- NONE：默认的，不显示任何日志；
- BASIC：仅记录请求方法、URL、响应状态码及执行时间；
- HEADERS：除了BASIC中定义的信息之外，还有请求和响应的头信息；
- FULL：除了HEADERS中定义的信息之外，还有请求和响应的正文及元数据。

### 通过配置开启更为详细的日志

> 我们通过java配置来使Feign打印最详细的Http请求日志信息。

```
/**
 * @author micheal.wang <a href="michael.won007@gmail.com"/>
 * @create 2020-02-08
 */
@Configuration
public class FeignConfig {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
```

## Feign的常用配置

### Feign自己的配置

```
feign:
  hystrix:
    enabled: true #在Feign中开启Hystrix
  compression:
    request:
      enabled: false #是否对请求进行GZIP压缩
      mime-types: text/xml,application/xml,application/json #指定压缩的请求数据类型
      min-request-size: 2048 #超过该大小的请求会被压缩
    response:
      enabled: false #是否对响应进行GZIP压缩
logging:
  level: #修改日志级别
    com.macro.cloud.service.UserService: debug
```

## 使用到的模块

```
dount-cloud-learning
├── dount-eureka-server -- eureka注册中心
├── dount-user-service -- 提供User对象CRUD接口的服务
└── dount-feign-service -- feign服务调用测试服务
```

