package com.example.RestAPIservicebookStore.controller;

import com.example.RestAPIservicebookStore.entity.Book;
import com.example.RestAPIservicebookStore.exception.BookAlreadyExistException;
import com.example.RestAPIservicebookStore.exception.BookDoesntExistException;
import com.example.RestAPIservicebookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    private ResponseEntity addNewBook(@RequestBody Book book){
        try {
            bookService.addNewBook(book);
            return ResponseEntity.ok("Книга успешно добавлена в коллекцию.");
        } catch (BookAlreadyExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Ошибка добавления новой книги в коллекцию.");
        }
    }

    @GetMapping("/all")
    public ResponseEntity getAllBooks(){
        try {
            return ResponseEntity.ok(bookService.getAllBooks());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка вывода на экран всех книг.");
        }
    }

    @GetMapping("/get")
    public ResponseEntity getBookByTitle(@RequestParam String title){
        try {
            return ResponseEntity.ok(bookService.getBookByTitle(title));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка вывода на экран книги с названием: " + title + ".");
        }
    }

    @PutMapping("/update/{title}")
    public ResponseEntity updateBook(@PathVariable String title,
                                     @RequestBody Book changedBook){
        try {
            return ResponseEntity.ok(bookService.updateBook(title,changedBook));
        } catch (BookDoesntExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка обновления данных книги.");
        }
    }
    @DeleteMapping("/del/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(bookService.deleteBookById(id));
        } catch (BookDoesntExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка удаления книги из коллекции.");
        }
    }





}
