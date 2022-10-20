package com.example.RestAPIservicebookStore.controller;

import com.example.RestAPIservicebookStore.entity.Customer;
import com.example.RestAPIservicebookStore.exception.CustomerAlreadyExistException;
import com.example.RestAPIservicebookStore.exception.CustomerDoesntExistException;
import com.example.RestAPIservicebookStore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/add")
    private ResponseEntity registration(@RequestBody Customer customer){
        try {
            customerService.registration(customer);
            return ResponseEntity.ok("Пользователь успешно создан.");
        } catch (CustomerAlreadyExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
             return ResponseEntity.badRequest().body("Ошибка регистрации пользователя.");
        }
    }

    @GetMapping("/")
    public ResponseEntity getCustomer(){
        try {
            return ResponseEntity.ok("Сервер работает");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка работы сервера.");
        }
    }

    @GetMapping("/get")
    public ResponseEntity getCustomerByEmail(@RequestParam String email){
        try {
            return ResponseEntity.ok(customerService.getByEmail(email));
        } catch (CustomerDoesntExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка просмотра пользовательских данных.");
        }
    }

    @PutMapping("/update/{username}")
    public ResponseEntity updateCustomer(@PathVariable String username,
                                         @RequestBody Customer changedCustomer){
        try {
            return ResponseEntity.ok(customerService.updateCustomer(username,changedCustomer));
        } catch (CustomerDoesntExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка обновления данных пользователя.");
        }
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(customerService.deleteById(id));
        } catch (CustomerDoesntExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка удаления клиента.");
        }
    }

}
