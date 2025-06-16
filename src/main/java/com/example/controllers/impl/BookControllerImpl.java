package com.example.controllers.impl;

import com.example.controllers.BookController;
import com.example.entities.Book;
import com.example.service.impl.BookServiceImpl;
import com.example.service.impl.Facade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookControllerImpl implements BookController {

    private final BookServiceImpl bookService;
    private final Facade facade;

    public ResponseEntity<HttpStatus> createBook(MultipartFile file) {
        facade.setBook(file);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.findAllBooks();
        return ResponseEntity.ok(books);
    }

    public ResponseEntity<Book> getBook(UUID id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }
}
