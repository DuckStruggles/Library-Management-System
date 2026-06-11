package com.srishti.libManagement.Service;

import com.srishti.libManagement.Model.BorrowRecord;
import com.srishti.libManagement.Repository.BorrowRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowRecordService {

    private final BorrowRecordRepository borrowRecordRepository;

    public BorrowRecordService(BorrowRecordRepository borrowRecordRepository) {
        this.borrowRecordRepository = borrowRecordRepository;
    }

    public BorrowRecord saveRecord(BorrowRecord record) {
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