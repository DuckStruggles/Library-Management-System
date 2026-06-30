package com.srishti.libManagement.Repository;

import com.srishti.libManagement.Model.Book;
import com.srishti.libManagement.Model.BorrowRecord;
import com.srishti.libManagement.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {

    List<BorrowRecord> findByUser(User user);

    List<BorrowRecord> findByBook(Book book);

    Optional<BorrowRecord> findByUserAndBookAndStatus(
            User user,
            Book book,
            String status
    );
}