package com.example.RestAPIservicebookStore.controller;

import com.example.RestAPIservicebookStore.entity.Book;
import com.example.RestAPIservicebookStore.exception.BookAlreadyExistException;
import com.example.RestAPIservicebookStore.exception.CustomerAlreadyExistException;
import com.example.RestAPIservicebookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    private ResponseEntity addNewBool(@RequestBody Book book){
        try {
            bookService.addNewBook(book);
            return ResponseEntity.ok("Книга успешно добавлена в коллекцию.");
        } catch (BookAlreadyExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Ошибка добавления новой книги в коллекцию.");
        }
    }





}
