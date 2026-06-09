package com.srishti.libManagement.Controller;

import com.srishti.libManagement.DTO.BookRequestDTO;
import com.srishti.libManagement.DTO.BookResponseDTO;
import com.srishti.libManagement.Service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import com.srishti.libManagement.DTO.BookSuggestionDTO;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService service;

    // GET all books
    @GetMapping
    public ResponseEntity<Page<BookResponseDTO>>
    getBooks(Pageable pageable) {

        return ResponseEntity.ok(
                service.getAllBooks(pageable)
        );
    }

    // GET book by ID
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBookByID(@PathVariable int id) {

        if(id <= 0) {
            throw new IllegalArgumentException("Invalid book ID: " + id);
        }

        return ResponseEntity.ok(service.getBookByID(id));
    }

    // GET books by author
    @GetMapping("/author/{author}")
    public ResponseEntity<List<BookResponseDTO>>
    getBooksByAuthor(@PathVariable String author) {

        return ResponseEntity.ok(service.getBookByAuthor(author));
    }

    // GET books by title
    @GetMapping("/title/{title}")
    public ResponseEntity<List<BookResponseDTO>>
    getBooksByTitle(@PathVariable String title) {

        return ResponseEntity.ok(service.getBooksByTitle(title));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<BookResponseDTO>>
    searchBooks(
            @RequestParam String keyword,
            Pageable pageable
    ) {

        return ResponseEntity.ok(service.searchBooks(keyword, pageable));
    }

    @GetMapping("/suggestions")
    public ResponseEntity<List<BookSuggestionDTO>>
    getSuggestions(@RequestParam String q) {

        return ResponseEntity.ok(
                service.getSuggestions(q)
        );
    }

    // ADD new book
    @PostMapping
    public ResponseEntity<BookResponseDTO>
    addBook(@Valid @RequestBody BookRequestDTO dto) {

        return ResponseEntity.ok(service.addBook(dto));
    }

    // UPDATE existing book
    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO>
    updateBook(@PathVariable long id,
               @Valid @RequestBody BookRequestDTO dto) {

        return ResponseEntity.ok(service.updateBook(id, dto));
    }

    // DELETE book
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {

        service.deleteBook(id);

        return ResponseEntity.ok("Book deleted successfully");
    }
}