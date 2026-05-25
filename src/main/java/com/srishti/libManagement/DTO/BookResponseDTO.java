package com.srishti.libManagement.DTO;

public class BookResponseDTO {

    private Long id;

    private String title;

    private String author;

    private String isbn;

    private String category;

    private int totalCopies;

    private int availableCopies;

    private int publishedYear;

    public BookResponseDTO(Long id,
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

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getCategory() {
        return category;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public int getPublishedYear() {
        return publishedYear;
    }
}