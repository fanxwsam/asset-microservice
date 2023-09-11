package com.asset.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    //List<Customer> getCustomersByLastName();
    List<Customer> findCustomersByLastName(String lastName);

}
