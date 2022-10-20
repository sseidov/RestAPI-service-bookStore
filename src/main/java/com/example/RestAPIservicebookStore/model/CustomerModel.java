package com.example.RestAPIservicebookStore.model;

import com.example.RestAPIservicebookStore.entity.Customer;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerModel {

    private int discount;
    private String email;
    private String firstName;
    private String lastName;

    public CustomerModel() {
    }

    public static CustomerModel toModel(Customer customer){
        CustomerModel customerModel = new CustomerModel();
        customerModel.setDiscount(customer.getDiscount());
        customerModel.setEmail(customer.getEmail());
        customerModel.setFirstName(customer.getFirstName());
        customerModel.setLastName(customer.getLastName());
        return customerModel;
    }
}
