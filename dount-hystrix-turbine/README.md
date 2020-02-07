## Hystrix 集群实例监控

这里我们使用Turbine来聚合hystrix-service服务的监控信息，然后我们的hystrix-dashboard服务就可以从Turbine获取聚合好的监控信息展示给我们了。

### 创建一个turbine-service模块

> 用来聚合hystrix-service的监控信息。

### 启动相关服务

> 使用application-replica1.yml配置再启动一个hystrix-service服务，启动turbine-service服务，此时注册中心显示如下。

![hystrix集群实例注册中心](D:\workspace\git\dount-cloud-learn\dount-hystrix-turbine\src\main\resources\file\hystrix集群实例注册中心.png)

### Hystrix集群监控演示

- 访问Hystrix Dashboard：http://localhost:8501/hystrix
- 添加集群监控地址，需要注意的是我们需要添加的是turbine-service的监控端点地址：



![hystrix集群实例登录页](D:\workspace\git\dount-cloud-learn\dount-hystrix-turbine\src\main\resources\file\hystrix集群实例登录页.JPG)

调用几次hystrix-service的接口：http://localhost:8401/user/testCommand/1以及http://localhost:8402/user/testCommand/1

![hystrix集群实例详情页](D:\workspace\git\dount-cloud-learn\dount-hystrix-turbine\src\main\resources\file\hystrix集群实例详情页.JPG)

可以看到我们的Hystrix实例数量变成了两个。