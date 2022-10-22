package com.example.RestAPIservicebookStore.service;

import com.example.RestAPIservicebookStore.entity.Book;
import com.example.RestAPIservicebookStore.exception.BookAlreadyExistException;
import com.example.RestAPIservicebookStore.exception.BookDoesntExistException;
import com.example.RestAPIservicebookStore.model.BookModel;
import com.example.RestAPIservicebookStore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public BookModel getBookByTitle(String title) throws BookDoesntExistException {
        Book book = bookRepository.findByTitle(title);
        if(book == null){
            throw new BookDoesntExistException("Книги с таким названием нет в каталоге.");
        }
        return BookModel.toModel(book);
    }

    public Book getBookById(Long id) throws BookDoesntExistException {
        Book book = bookRepository.findById(id).get();
        if(book == null){
            throw new BookDoesntExistException("Книги с таким id нет в каталоге.");
        }
        return book;
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }


    public List<BookModel> getAllBooks(){
        List<Book> books = new ArrayList<>();
        List<BookModel> bookModels = new ArrayList<>();
        books.addAll(bookRepository.findAll());

        return BookModel.listToModel(books);
    }

    public BookModel updateBook(String title, Book updatedBook) throws BookDoesntExistException {
        Book book = bookRepository.findByTitle(title);
        if(book == null){
            throw new BookDoesntExistException("Книга с таким названием не найдена.");
        }
        book.setPrice(updatedBook.getPrice());
        bookRepository.save(book);

        return BookModel.toModel(book);
    }

    public Long deleteBookById(Long id) throws BookDoesntExistException {
        if (bookRepository.findById(id) == null){
            throw new BookDoesntExistException("Книги с таким id нет в коллекции.");
        }
        bookRepository.deleteById(id);
        return id;
    }
}
