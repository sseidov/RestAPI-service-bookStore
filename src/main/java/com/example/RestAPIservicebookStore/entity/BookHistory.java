package com.example.RestAPIservicebookStore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "book_history")
@Setter
@Getter
public class BookHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private java.util.Date startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private java.util.Date endDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false)
    private Book book;
}
