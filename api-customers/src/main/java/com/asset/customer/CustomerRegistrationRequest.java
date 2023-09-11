package com.asset.customer;

import org.springframework.stereotype.Service;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {

}
