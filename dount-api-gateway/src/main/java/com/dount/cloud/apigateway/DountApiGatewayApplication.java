package com.dount.cloud.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DountApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DountApiGatewayApplication.class, args);
	}

}
