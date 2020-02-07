# Hystrix Dashboard：断路器执行监控

Hystrix Dashboard 是Spring Cloud中查看Hystrix实例执行情况的一种仪表盘组件，支持查看单个实例和查看集群实例。Hystrix提供了Hystrix Dashboard来实时监控HystrixCommand方法的执行情况。 Hystrix Dashboard可以有效地反映出每个Hystrix实例的运行情况，帮助我们快速发现系统中的问题，从而采取对应措施。

### Hystrix实例监控演示

- 访问Hystrix Dashboard：http://localhost:8501/hystrix

![](D:\workspace\git\dount-cloud-learn\dount-hystrix-dashboard\src\main\resources\file\Hystrix单个实例访问页.png)

- 注: 被监控的hystrix-service服务需要开启Actuator的hystrix.stream端点，配置信息如下：

```
management:
  endpoints:
    web:
      exposure:
        include: 'hystrix.stream' #暴露hystrix监控端点
```


调用几次hystrix-service的接口：http://localhost:8401/user/testCommand/1

![hystrix.stream 监控hystrix-service请求调用性能](D:\workspace\git\dount-cloud-learn\dount-hystrix-dashboard\src\main\resources\file\hystrix单个实例详情.png)

可以发现曾经我们在@HystrixCommand中添加的commandKey和threadPoolKey属性都显示在上面了，并且有14次调用都成功了。

### Hystrix Dashboard 图表解读

> 图表解读如下，需要注意的是，小球代表该实例健康状态及流量情况，颜色越显眼，表示实例越不健康，小球越大，表示实例流量越大。曲线表示Hystrix实例的实时流量变化。



![hystrix.stream 图表解读](D:\workspace\git\dount-cloud-learn\dount-hystrix-dashboard\src\main\resources\file\hystrix.单个实例图表解读.png)

