Spring Cloud Eureka是Spring Cloud Netflix 子项目的核心组件之一，主要用于微服务架构中的服务治理。

## Eureka简介

在微服务架构中往往会有一个注册中心，每个微服务都会向注册中心去注册自己的地址及端口信息，注册中心维护着服务名称与服务实例的对应关系。每个微服务都会定时从注册中心获取服务列表，同时汇报自己的运行情况，这样当有的服务需要调用其他服务时，就可以从自己获取到的服务列表中获取实例地址进行调用，Eureka实现了这套服务注册与发现机制。



## 搭建Eureka注册中心

- eureka-server依赖

```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
```

- 在启动类上添加@EnableEurekaServer注解来启用Euerka注册中心功能
- 运行完成后访问地址http://localhost:8001/可以看到Eureka注册中心的界面

![](D:\workspace\git\dount-cloud-learn\dount-eureka-server\src\main\resources\file\Eureka 注册中心.png)



## 搭建Eureka客户端

- eureka-client依赖

```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

- 在启动类上添加@EnableDiscoveryClient注解表明是一个Eureka客户端

- 查看注册中心http://localhost:8001/发现Eureka客户端已经成功注册

  ![](D:\workspace\git\dount-cloud-learn\dount-eureka-server\src\main\resources\file\Eureka注册成功.png)



## 搭建Eureka注册中心集群

### 搭建两个注册中心

> 由于所有服务都会注册到注册中心去，服务之间的调用都是通过从注册中心获取的服务列表来调用，注册中心一旦宕机，所有服务调用都会出现问题。所以我们需要多个注册中心组成集群来提供服务。