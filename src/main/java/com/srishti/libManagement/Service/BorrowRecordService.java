package com.srishti.libManagement.Service;

import com.srishti.libManagement.Model.Book;
import com.srishti.libManagement.Model.BorrowRecord;
import com.srishti.libManagement.Model.User;
import com.srishti.libManagement.Repository.BookRepository;
import com.srishti.libManagement.Repository.BorrowRecordRepository;
import com.srishti.libManagement.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowRecordService {

    private final BorrowRecordRepository borrowRecordRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BorrowRecordService(
            BorrowRecordRepository borrowRecordRepository,
            BookRepository bookRepository,
            UserRepository userRepository) {

        this.borrowRecordRepository = borrowRecordRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public BorrowRecord borrowBook(Long userId, Long bookId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("Book is currently unavailable");
        }

        borrowRecordRepository
                .findByUserAndBookAndStatus(user, book, "BORROWED")
                .ifPresent(record -> {
                    throw new RuntimeException("You have already borrowed this book.");
                });

        BorrowRecord record = new BorrowRecord();

        record.setUser(user);
        record.setBook(book);
        record.setIssueDate(LocalDate.now());
        record.setDueDate(LocalDate.now().plusDays(14));
        record.setStatus("BORROWED");

        book.setAvailableCopies(book.getAvailableCopies() - 1);

        bookRepository.save(book);

        return borrowRecordRepository.save(record);
    }

    public BorrowRecord returnBook(Long recordId) {

        BorrowRecord record = borrowRecordRepository.findById(recordId)
                .orElseThrow(() -> new RuntimeException("Borrow record not found"));

        if ("RETURNED".equals(record.getStatus())) {
            throw new RuntimeException("Book has already been returned.");
        }

        Book book = record.getBook();

        record.setReturnDate(LocalDate.now());
        record.setStatus("RETURNED");

        book.setAvailableCopies(book.getAvailableCopies() + 1);

        bookRepository.save(book);

        return borrowRecordRepository.save(record);
    }

    public List<BorrowRecord> getAllRecords() {
        return borrowRecordRepository.findAll();
    }

    public BorrowRecord getRecordById(Long id) {
        return borrowRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Borrow record not found"));
    }

    public void deleteRecord(Long id) {
        borrowRecordRepository.deleteById(id);
    }
}