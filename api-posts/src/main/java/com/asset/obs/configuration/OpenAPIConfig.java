package com.asset.obs.configuration;

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
            .info(new Info().title("Audition Application API")
                .description("The audition  manages audition posts and comments. \n\n"
                    + "It provides endpoints to retrieve audition posts and associated comments. \n\n"
                    + "Original data can be found in the link below:\n\n"
                    + " <a>https://jsonplaceholder.typicode.com/</a>")
            );
    }

    @Bean
    ForwardedHeaderFilter forwardedHeaderFilter() {
        return new ForwardedHeaderFilter();
    }
}