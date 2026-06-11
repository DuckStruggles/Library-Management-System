package com.srishti.libManagement.Repository;

import com.srishti.libManagement.Model.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
}