package com.asset.fraud;

import com.asset.clients.fraud.FraudCheckResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/fraud")
public class FraudCheckController {
    private final FraudCheckService fraudCheckService;

    @Operation(
            summary = "Check if the customer with the email is a fraudster",
            description = "Query the fraudster table and save this fraud checking to checking history table",
            tags = { "fraud-check", "GET" })
    @GetMapping(path="{email}")
    public FraudCheckResponse isFraudster(@PathVariable("email") String email){
        boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(email);
        log.info("Fraud Check, email address: " + email);
        return new FraudCheckResponse(isFraudulentCustomer);
    }

    @Operation(
            summary = "Add one fraudster",
            description = "Save fraudster email address",
            tags = { "fraud-record", "POST" })
    @PostMapping("/fraud-record")
    public String createFraudster(@RequestBody String email){
        fraudCheckService.createFraudRecord(email);
        log.info("Fraud create, email: " + email);
        return "created a fraudster in database, email: " + email;
    }

    @Operation(
            summary = "Query fraudster table with email address",
            description = "Get a list from fraudster table",
            tags = { "fraud-record", "GET" })
    @GetMapping("/fraud-record/{email}")
    public List<FraudEntity> getFraudster(@PathVariable("email") String email){
        return fraudCheckService.getFraudsters(email);
    }
}