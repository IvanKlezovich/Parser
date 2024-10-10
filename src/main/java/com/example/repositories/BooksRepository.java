package com.example.repositories;

import com.example.entities.Book;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BooksRepository {
    Book save(Book book);
    List<Book> findAll();
    Optional<Book> findById(UUID id);
}
