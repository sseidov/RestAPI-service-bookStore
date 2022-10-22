package com.example.RestAPIservicebookStore.model;

import com.example.RestAPIservicebookStore.entity.Author;
import com.example.RestAPIservicebookStore.entity.Book;
import com.example.RestAPIservicebookStore.entity.Customer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class BookModel {

    private String title;
    private String category;
    private BigDecimal price;
    @Temporal(TemporalType.DATE)
    private java.util.Date date;
    private Set<Author> enrolledAuthors = new HashSet<>();


    public static BookModel toModel(Book book){
        BookModel bookModel = new BookModel();
        bookModel.setTitle(book.getTitle());
        bookModel.setCategory(book.getCategory());
        bookModel.setPrice(book.getPrice());
        bookModel.setDate(book.getDate());
        bookModel.setEnrolledAuthors(book.getEnrolledAuthors());
        return bookModel;
    }

    public static List<BookModel> listToModel(List<Book> books){
        List<BookModel> bookModelList = new ArrayList<>();
        for (Book b: books) {
            bookModelList.add(BookModel.toModel(b));
        }
        return bookModelList;
    }
}
