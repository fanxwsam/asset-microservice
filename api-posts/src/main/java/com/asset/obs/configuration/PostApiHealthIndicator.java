package com.asset.obs.configuration;

import com.asset.obs.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class PostApiHealthIndicator implements HealthIndicator {
    private static final String URL = "https://jsonplaceholder.typicode.com/posts";
    private final RestTemplate restTemplate;

    @Override
    public Health health() {
        try {
            ResponseEntity<Post[]> response = restTemplate.getForEntity(URL, Post[].class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return Health.up()
                        .withDetail("success", true)
                        .build();
            } else {
                return Health.down()
                        .withDetail("ping_url", URL)
                        .withDetail("error_code", response.getStatusCode())
                        .build();
            }
        } catch (RestClientException e) {
            return Health.down()
                    .withDetail("ping_url", URL)
                    .withDetail("error", e.getMessage())
                    .build();
        }
    }
}
