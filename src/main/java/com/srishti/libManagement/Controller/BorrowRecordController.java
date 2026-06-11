package com.srishti.libManagement.Controller;

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

    @PostMapping
    public BorrowRecord createRecord(
            @RequestBody BorrowRecord borrowRecord) {

        return borrowRecordService.saveRecord(borrowRecord);
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