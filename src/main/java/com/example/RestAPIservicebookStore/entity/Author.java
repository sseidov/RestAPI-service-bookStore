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

    @Column(name = "firsName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "dateOfBirth")
    @Temporal(TemporalType.DATE)
    private java.util.Date dateOfBirth;

    @JsonIgnore
    @ManyToMany(mappedBy = "enrolledAuthors")
    private Set<Book> books = new HashSet<>();

}
