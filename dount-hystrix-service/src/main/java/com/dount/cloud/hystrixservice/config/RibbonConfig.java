package com.dount.cloud.hystrixservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author micheal.wang <a href="michael.won007@gmail.com"/>
 * @create 2020-02-07
 */
@Configuration
public class RibbonConfig {

    /**
     * @description: Ribbon的负载均衡：RestTemplate添加@LoadBalanced
     * @author micheal.wang <a href="michael.won007@gmail.com"/>
     * @create 2020-02-07
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
