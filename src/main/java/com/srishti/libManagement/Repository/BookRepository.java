package com.srishti.libManagement.Repository;

import com.srishti.libManagement.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> { //JpaRepository<EntityType, PrimaryKeyType>
    List<Book> findByAuthor(String author);
    List<Book> findByTitle(String title);
}
