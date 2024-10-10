package com.example.service;

import com.example.entities.Book;

import java.io.File;
import java.util.List;
import java.util.UUID;

public interface BookService {

    Book saveBook(File file);

    List<Book> findAllBooks();

    Book getBookById(UUID id);

    int count();
}
