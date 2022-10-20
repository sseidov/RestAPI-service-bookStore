package com.example.RestAPIservicebookStore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "author")
@Getter
@Setter
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "fullName", nullable = false)
    private String fullName;

    @JsonIgnore
    @ManyToMany(mappedBy = "enrolledAuthors")
    private Set<Book> books = new HashSet<>();


}
