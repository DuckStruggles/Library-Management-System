package com.srishti.libManagement.Controller;

import com.srishti.libManagement.DTO.BorrowRequestDTO;
import com.srishti.libManagement.Model.BorrowRecord;
import com.srishti.libManagement.Service.BorrowRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrow-records")
@CrossOrigin("*")
public class BorrowRecordController {

    private final BorrowRecordService borrowRecordService;

    public BorrowRecordController(BorrowRecordService borrowRecordService) {
        this.borrowRecordService = borrowRecordService;
    }
    @PostMapping("/borrow")
    public BorrowRecord borrowBook(@RequestBody BorrowRequestDTO request) {
        return borrowRecordService.borrowBook(
                request.getUserId(),
                request.getBookId()
        );
    }

    @PutMapping("/return/{recordId}")
    public BorrowRecord returnBook(@PathVariable Long recordId) {

        return borrowRecordService.returnBook(recordId);
    }

    @GetMapping
    public List<BorrowRecord> getAllRecords() {
        return borrowRecordService.getAllRecords();
    }

    @GetMapping("/{id}")
    public BorrowRecord getRecordById(@PathVariable Long id) {
        return borrowRecordService.getRecordById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable Long id) {
        borrowRecordService.deleteRecord(id);
    }
}