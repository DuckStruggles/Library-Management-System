package com.srishti.libManagement.Service;

import com.srishti.libManagement.DTO.BookRequestDTO;
import com.srishti.libManagement.DTO.BookResponseDTO;
import com.srishti.libManagement.DTO.BookSuggestionDTO;
import com.srishti.libManagement.Model.Book;
import com.srishti.libManagement.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    // Convert Entity -> ResponseDTO
    private BookResponseDTO mapToDTO(Book book) {

        return new BookResponseDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getCategory(),
                book.getTotalCopies(),
                book.getAvailableCopies(),
                book.getPublishedYear()
        );
    }

    // GET all books
    public Page<BookResponseDTO>
    getAllBooks(Pageable pageable) {

        return repository.findAll(pageable)
                .map(this::mapToDTO);
    }

    // GET by ID
    public BookResponseDTO getBookByID(int id) {

        Book book = repository.findById((long) id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Book with ID: " + id + " does not exist"));

        return mapToDTO(book);
    }

    // GET by author
    public List<BookResponseDTO> getBookByAuthor(String author) {

        List<Book> books = repository.findByAuthorContainingIgnoreCase(author);

        if(books.isEmpty()) {
            throw new IllegalArgumentException(
                    "Author - " + author + " does not exist");
        }

        return books.stream()
                .map(this::mapToDTO)
                .toList();
    }

    // GET by title
    public List<BookResponseDTO> getBooksByTitle(String title) {
        List<Book> books = repository.findByTitleContainingIgnoreCase(title);

        if(books.isEmpty()) {
            throw new IllegalArgumentException(
                    "No books with title '" + title + "' found");
        }

        return books.stream()
                .map(this::mapToDTO)
                .toList();
    }

    public Page<BookResponseDTO>
    searchBooks(String keyword,
                Pageable pageable) {

        return repository
                .findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(
                        keyword,
                        keyword,
                        pageable
                )
                .map(this::mapToDTO);
    }

    public List<BookSuggestionDTO>
    getSuggestions(String keyword) {

        return repository
                .findTop5ByTitleContainingIgnoreCaseOrderByTitleAsc(
                        keyword
                )
                .stream()
                .map(book ->
                        new BookSuggestionDTO(
                                book.getId(),
                                book.getTitle(),
                                book.getAuthor()
                        )
                )
                .toList();
    }

    // ADD book
    public BookResponseDTO addBook(BookRequestDTO dto) {

        Book book = new Book();

        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        book.setCategory(dto.getCategory());
        book.setTotalCopies(dto.getTotalCopies());
        book.setAvailableCopies(dto.getAvailableCopies());
        book.setPublishedYear(dto.getPublishedYear());

        Book savedBook = repository.save(book);

        return mapToDTO(savedBook);
    }

    // UPDATE book
    public BookResponseDTO updateBook(Long id,
                                      BookRequestDTO dto) {

        Book existingBook = repository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Book with ID " + id + " not found"));

        existingBook.setTitle(dto.getTitle());
        existingBook.setAuthor(dto.getAuthor());
        existingBook.setIsbn(dto.getIsbn());
        existingBook.setCategory(dto.getCategory());
        existingBook.setTotalCopies(dto.getTotalCopies());
        existingBook.setAvailableCopies(dto.getAvailableCopies());
        existingBook.setPublishedYear(dto.getPublishedYear());

        Book updatedBook = repository.save(existingBook);

        return mapToDTO(updatedBook);
    }

    // DELETE book
    public void deleteBook(int id) {

        repository.deleteById((long) id);
    }
}