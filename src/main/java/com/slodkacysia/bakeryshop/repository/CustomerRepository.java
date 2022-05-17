package com.slodkacysia.bakeryshop.repository;

import com.slodkacysia.bakeryshop.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer save(Customer customer);
    Customer findCustomerByUsername(String username);

    public Customer findCustomerById(long customerId);


}
