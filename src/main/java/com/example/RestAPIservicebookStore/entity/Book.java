package com.example.RestAPIservicebookStore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "book")
@Setter
@Getter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "category")
    private String category;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private java.util.Date date;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> enrolledAuthors = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "book")
    private Set<BookHistory> priceStory = new HashSet<>();

    public void addPriceHistoryToBook(BookHistory bookHistory){
        priceStory.add(bookHistory);
    }

    public void enrollAuthorToBook(Author author) {
        enrolledAuthors.add(author);
    }

    public void deleteAuthorFromBook(Author author) {
        enrolledAuthors.remove(author);
    }
}
