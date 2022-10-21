package com.example.RestAPIservicebookStore.service;

import com.example.RestAPIservicebookStore.entity.Customer;
import com.example.RestAPIservicebookStore.exception.CustomerAlreadyExistException;
import com.example.RestAPIservicebookStore.exception.CustomerDoesntExistException;
import com.example.RestAPIservicebookStore.model.CustomerModel;
import com.example.RestAPIservicebookStore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer registration(Customer customer) throws CustomerAlreadyExistException {
        if(customerRepository.findByEmail(customer.getEmail()) != null){
            throw new CustomerAlreadyExistException("Пользователь с таким email уже зарегистрирован.");
        }
        if(customerRepository.findByUsername(customer.getUsername()) != null){
            throw new CustomerAlreadyExistException("Пользователь с таким username уже зарегистрирован.");
        }
        return customerRepository.save(customer);
    }

    public CustomerModel updateCustomer(String username, Customer updatedCustomer) throws CustomerDoesntExistException {
        Customer customer = customerRepository.findByUsername(username);

        if(customer == null){
            throw new CustomerDoesntExistException("Пользователь с таким username не найден.");
        }
        customer.setDiscount(updatedCustomer.getDiscount());
        customer.setFirstName(updatedCustomer.getFirstName());
        customer.setLastName(updatedCustomer.getLastName());
        customer.setPassword(updatedCustomer.getPassword());
        customerRepository.save(customer);

        return CustomerModel.toModel(customer);
    }

    public CustomerModel getByEmail(String email) throws CustomerDoesntExistException {
        Customer customer = customerRepository.findByEmail(email);
        if(customer == null){
            throw new CustomerDoesntExistException("Пользователя с таким email не существует.");
        }
        return CustomerModel.toModel(customer);
    }

    public Long deleteById(Long id) throws CustomerDoesntExistException{
        if(!customerRepository.findById(id).isPresent()){
            throw new CustomerDoesntExistException("Пользователя с таким id не существует.");
        }
        customerRepository.deleteById(id);
        return id;
    }


}
