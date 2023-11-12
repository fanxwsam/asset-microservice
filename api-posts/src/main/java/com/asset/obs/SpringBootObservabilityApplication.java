package com.asset.obs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootObservabilityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootObservabilityApplication.class, args);
    }


//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
//        return restTemplateBuilder.build();
//    }
}
