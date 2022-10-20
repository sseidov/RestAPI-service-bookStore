package com.example.RestAPIservicebookStore.repository;

import com.example.RestAPIservicebookStore.entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
    Book findByTitle(String title);
}
