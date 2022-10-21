package com.example.RestAPIservicebookStore.controller;

import com.example.RestAPIservicebookStore.entity.Author;
import com.example.RestAPIservicebookStore.exception.AuthorDoesntExistException;
import com.example.RestAPIservicebookStore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/add")
    private ResponseEntity addNewAuthor(@RequestBody Author author){
        try {
            authorService.addNewAuthor(author);
           return ResponseEntity.ok("Писатель успешно добавлен в БД.");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка добавления писателя в БД.");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCustomer(@PathVariable Long id,
                                         @RequestBody Author changedAuthor){
        try {
            return ResponseEntity.ok(authorService.updateAuthor(id, changedAuthor));
        } catch (AuthorDoesntExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка обновления данных пользователя.");
        }
    }

    @GetMapping("/get")
    public ResponseEntity getAuthorById (@RequestParam Long id){
        try {
            return ResponseEntity.ok(authorService.getAuthorById(id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка вывода на экран писателя с данным id =  " + id + ".");
        }
    }


    @DeleteMapping("/del/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(authorService.deleteAuthorById(id));
        } catch (AuthorDoesntExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка удаления писателя из БД.");
        }
    }

}
