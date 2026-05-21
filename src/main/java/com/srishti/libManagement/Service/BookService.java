package com.srishti.libManagement.Service;

import com.srishti.libManagement.Model.Book;
import com.srishti.libManagement.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository repository;

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public Book getBookByID(int id) {
        return repository.findById(id).orElseThrow( ()->
                new IllegalArgumentException("Book with ID: " + id+ " does not exit"));
    }

    public List<Book> getBookByAuthor(String author) {
        List<Book> books = repository.findByAuthor(author);
        if(books.isEmpty()) {
            throw new IllegalArgumentException("Author - " + author + " does not exist");
        }
        return books;
    }

    public List<Book> getBooksByTitle(String title) {
        List<Book> books = repository.findByTitle(title);
        if(books.isEmpty()) {
            throw new IllegalArgumentException("No books with title '" + title+"' found");
        }
        return books;
    }


    public Book addBook(Book book) {

        return repository.save(book);
    }

    public Book updateBook(int id, Book book) {
        Book existingBook=repository.findById(id).orElse(null);

        if(existingBook !=null) {
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());

            return repository.save(existingBook);
        }
        return null;
    }

    public void deleteBook(int id) {
        repository.deleteById(id);
    }
}
