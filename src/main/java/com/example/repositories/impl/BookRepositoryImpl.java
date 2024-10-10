package com.example.repositories.impl;

import com.example.entities.Book;
import com.example.repositories.BooksRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class BookRepositoryImpl implements BooksRepository {

    List<Book> books = new ArrayList<>();

    @Override
    public Book save(Book book) {
        book.setId(UUID.randomUUID());
        books.add(book);
        return book;
    }

    @Override
    public List<Book> findAll() {
        return books;
    }

    @Override
    public Optional<Book> findById(UUID id) {
        return books.stream()
                .filter(book -> id.equals(book.getId()))
                .findFirst();
    }
}
