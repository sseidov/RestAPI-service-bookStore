package com.example.RestAPIservicebookStore.service;

import com.example.RestAPIservicebookStore.entity.Book;
import com.example.RestAPIservicebookStore.entity.Customer;
import com.example.RestAPIservicebookStore.exception.BookAlreadyExistException;
import com.example.RestAPIservicebookStore.exception.CustomerAlreadyExistException;
import com.example.RestAPIservicebookStore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addNewBook(Book book) throws BookAlreadyExistException {
        if ( bookRepository.findByTitle(book.getTitle()) != null){
            throw new BookAlreadyExistException("Книга с таким названием уже существует.");
        }
        return bookRepository.save(book);
    }




}
