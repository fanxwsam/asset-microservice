package com.asset.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "api-fraud",
        url = "${clients.fraud.url}"
)
public interface FraudClient {
    @GetMapping(path = "api/v1/fraud/{emailAddress}")
    FraudCheckResponse isFraudster(@PathVariable("emailAddress") String email,
                                   @RequestHeader("Authorization") String authorizationHeader);
}
