package com.example.RestAPIservicebookStore.repository;

import com.example.RestAPIservicebookStore.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findByEmail(String email);
    Customer findByUsername(String username);
}
