package com.dount.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DountEurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(DountEurekaClientApplication.class, args);
	}

}
