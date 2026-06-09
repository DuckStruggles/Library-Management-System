package com.srishti.libManagement.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    private String isbn;

    private String category;

    private int totalCopies;

    private int availableCopies;

    private int publishedYear;

    public Book() {
    }

    public Book(Long id,
                String title,
                String author,
                String isbn,
                String category,
                int totalCopies,
                int availableCopies,
                int publishedYear) {

        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
        this.publishedYear = publishedYear;
    }

}