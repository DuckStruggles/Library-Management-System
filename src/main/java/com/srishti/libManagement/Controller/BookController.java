package com.srishti.libManagement.Controller;

import com.srishti.libManagement.Model.Book;
import com.srishti.libManagement.Service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService service;

    // GET all books
    @GetMapping
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.ok(service.getAllBooks());
    }

    // GET book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book>
    getBookByID(@PathVariable int id) {
        if(id<0) {
            throw new IllegalArgumentException("Invalid book ID: " + id);
        }
        return ResponseEntity.ok(service.getBookByID(id));
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<Book>>
    getBooksByAuthor(@PathVariable String author) {
        return ResponseEntity.ok(service.getBookByAuthor(author));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Book>>
    getBooksByTitle(@PathVariable String title) {
        return ResponseEntity.ok(service.getBooksByTitle(title));
    }

    // ADD new book
    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
        return ResponseEntity.ok(service.addBook(book));
    }

    // UPDATE existing book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id,
                                           @Valid @RequestBody Book updatedBook) {
        return ResponseEntity.ok(service.updateBook(id, updatedBook));
    }

    // DELETE book
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {
        service.deleteBook(id);
        return ResponseEntity.ok("Book deleted successfully");
    }
}