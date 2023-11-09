package com.asset.graphql.controller;

import com.asset.graphql.dto.CustomerResponse;
import com.asset.graphql.dto.PurchaseTransactionResponse;
import com.asset.graphql.service.CustomerService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Controller
public record CustomerGraphQLController(CustomerService customerService) {

    @QueryMapping
    public String hello() {
        return "Hello, world!";
    }

    @QueryMapping(name = "customers")
    public List<CustomerResponse> getAllCustomersWithFilters(
            @Argument String fullName,
            @Argument String phoneNumber,
            @Argument @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate createdAt,
            @Argument String paymentType) {
        return customerService.getAllCustomersWithFilters(fullName, phoneNumber, createdAt, paymentType);
    }

    @QueryMapping(name = "customer")
    public CustomerResponse getCustomerById(@Argument @NotNull String customerId) {
        return customerService.getCustomersById(customerId);
    }


    @QueryMapping(name = "customerTransactions")
    public List<PurchaseTransactionResponse> getAllCustomerPurchaseTransactions(@Argument @NotNull String customerId) {
        return customerService.getAllCustomerPurchaseTransactions(customerId);
    }

    //@QueryMapping(name = "customerTransaction")
    @SchemaMapping(typeName = "Query", value = "customerTransaction")
    public PurchaseTransactionResponse getCustomerPurchaseTransactionById(@Argument @NotNull String customerId,
                                                       @Argument @NotNull String purchaseTransactionId) {
        return customerService.getCustomerPurchaseTransactionsById(customerId, purchaseTransactionId);
    }

}
