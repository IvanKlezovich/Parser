package com.example.dtos;

import com.example.entities.Chapter;

import java.util.List;
import java.util.UUID;

public class BookDto {
    private UUID id;
    private String title;
    private String author;
    private List<Chapter> chapters;
}
