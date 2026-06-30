package com.srishti.libManagement.DTO;

public class BorrowRequestDTO {

    private Long userId;

    private Long bookId;

    public BorrowRequestDTO() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}