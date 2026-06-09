package com.srishti.libManagement.DTO;

public class BookSuggestionDTO {

    private Long id;
    private String title;
    private String author;

    public BookSuggestionDTO(Long id,
                             String title,
                             String author) {

        this.id = id;
        this.title = title;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}