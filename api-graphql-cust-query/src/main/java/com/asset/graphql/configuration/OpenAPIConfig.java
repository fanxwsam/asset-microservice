package com.asset.graphql.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ForwardedHeaderFilter;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("Graphql Query API - customers and their purchase transactions")
                        .description("The operations match to the 2 entities: " +
                                "customer \n\n" +
                                "PurchaseTransaction \n\n" +
                                "Details can be find in link:\n\n <a>https://xxxx</a>")
                        );
    }

    @Bean
    ForwardedHeaderFilter forwardedHeaderFilter() {
        return new ForwardedHeaderFilter();
    }
}