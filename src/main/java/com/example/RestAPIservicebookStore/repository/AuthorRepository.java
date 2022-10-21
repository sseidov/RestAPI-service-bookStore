package com.example.RestAPIservicebookStore.repository;

import com.example.RestAPIservicebookStore.entity.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    Author findByFirstName(String firstName);
    Author findByLastNameAndDateOfBirth(String firstName, Date date);

}
