package com.example.service.impl;

import com.example.entities.Book;
import com.example.entities.Parser;
import com.example.repositories.impl.BookRepositoryImpl;
import com.example.service.BookService;
import com.example.utils.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepositoryImpl booksRepositoryImpl;
    private final BookMapper bookMapper;

    public Book saveBook(File file) {
        Parser parser = new Parser();
        Book book = parser.getBook(file);
        booksRepositoryImpl.save(book);
        return book;
    }

    public List<Book> findAllBooks() {
        List<Book> books = booksRepositoryImpl.findAll();
        return books;
    }

    public Book getBookById(UUID id) {
        return booksRepositoryImpl.findById(id)
                .orElse(null);
    }

    public int count() {
        return booksRepositoryImpl.findAll().size();
    }
}
