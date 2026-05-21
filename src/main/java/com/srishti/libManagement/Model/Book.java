package com.srishti.libManagement.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Book {
    @Id
    @GeneratedValue
    private int id;

    @NotBlank(message = "Name is mandatory")
    private String title;

    @NotBlank(message = "Author name is mandatory")
    private String author;

    public Book() {
    }

    public Book(int id, String title, String author) {
        this.id=id; //obj ref = passed ref
        this.title = title;
        this.author=author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
