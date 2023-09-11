package com.asset.customer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Customer", description = "Customer management APIs")
@Slf4j
@RestController
@RequestMapping("api/v1/")
@AllArgsConstructor
public class CustomerController {

    private CustomerService customerService;

    @Operation(
            summary = "Save one customer to database",
            description = "Save customer, the key will be generated automatically. The response is the saved customer",
            tags = { "Customer", "POST" })
    @PostMapping("customers")
    public String registerCustomer(@RequestBody CustomerRegistrationRequest customerRequest){
        log.info("New customer registration {}", customerRequest.toString());
        return customerService.registerCustomer(customerRequest);
    }

    @Operation(
            summary = "Get one customer from database",
            description = "Query Criteria is lastName",
            tags = { "Customer", "GET" })
    @GetMapping("customers")
    public List<Customer> registerCustomer(@RequestBody Customer customer){
        log.info("customer {}", customer.toString());

        return customerService.getCustomers(customer);
    }

}
