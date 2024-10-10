package com.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Component
@RequiredArgsConstructor
public class Facade {

    private final BookServiceImpl bookService;

    public boolean setBook(MultipartFile file) {
        File file1 = new File("1" + file.getOriginalFilename());
        try (OutputStream os = new FileOutputStream(file1)) {
            os.write(file.getBytes());
            bookService.saveBook(file1);
            file1.delete();
            return true;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
