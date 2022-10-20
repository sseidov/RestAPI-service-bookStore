package com.example.RestAPIservicebookStore.repository;

import com.example.RestAPIservicebookStore.entity.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findByEmail(String email);
    Customer findByUsername(String username);

//    @Modifying
//    @Query("delete from Customer c where c.email = ?1")
//    void delete(String email);
}
