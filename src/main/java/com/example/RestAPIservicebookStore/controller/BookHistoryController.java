package com.example.RestAPIservicebookStore.controller;

import com.example.RestAPIservicebookStore.entity.Author;
import com.example.RestAPIservicebookStore.entity.Book;
import com.example.RestAPIservicebookStore.entity.BookHistory;
import com.example.RestAPIservicebookStore.exception.BookAlreadyExistException;
import com.example.RestAPIservicebookStore.exception.BookDoesntExistException;
import com.example.RestAPIservicebookStore.service.BookHistoryService;
import com.example.RestAPIservicebookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

@RestController
@RequestMapping("/historyPrice")
public class BookHistoryController {

    private BookHistoryService bookHistoryService;
    @Autowired
    public void setBookHistoryService(BookHistoryService bookHistoryService) {
        this.bookHistoryService = bookHistoryService;
    }

    private BookService bookService;
    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/bookId/{bookId}")
    public ResponseEntity addPriceToBook(@PathVariable Long bookId,
                                         @RequestBody BookHistory bookHistory){
        try {
            Book book = bookService.getBookById(bookId);
            bookHistory.setBook(book);
            bookHistory.setPrice(book.getPrice());

            book.addPriceHistoryToBook(bookHistory);
            bookHistoryService.setPrice(bookHistory);
            return ResponseEntity.ok("Стоимость для данной книги была зафиксирована.");
        } catch (BookDoesntExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка добавления стоимости для данной книги.");
        }
    }

//  Update books price
    @PutMapping("/bookId/{bookId}/upd")
    public ResponseEntity updateBook(@PathVariable Long bookId,
                                     @RequestBody BookHistory bookHistory){
        try {
            Book book = bookService.getBookById(bookId);
            book.setPrice(bookHistory.getPrice());
            bookService.saveBook(book);
            bookHistory.setBook(book);
            bookHistoryService.updatePrice(bookHistory, bookId);
            return ResponseEntity.ok("Стоимость для данной книги была изменена.");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка обновления данных о стоимости книги.");
        }
    }


}
