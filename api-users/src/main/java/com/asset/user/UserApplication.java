package com.asset.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//@OpenAPIDefinition(
//		info = @Info(contact = @Contact(name = "dev ahi",
//				email = "dev@ahi.tech"), title = "Product Service",
//				termsOfService = "www.ahi.tech",
//				description = "Backend API for data process",
//				license = @License(name = "product service licence", url = "www.ahi.tech/licence"),
//				version = "v1"))
@EnableEurekaClient
@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

}
