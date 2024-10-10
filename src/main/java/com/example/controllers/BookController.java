package com.example.controllers;

import com.example.entities.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface BookController {

    @PostMapping(value = "/create_book", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<HttpStatus> createBook(@RequestParam("file") MultipartFile file);

    @GetMapping("/books")
    ResponseEntity<List<Book>> getAllBooks();

    @GetMapping("/book")
    ResponseEntity<Book> getBook(@RequestBody UUID id);


}
