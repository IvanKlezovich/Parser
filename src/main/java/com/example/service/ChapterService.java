package com.example.service;

import com.example.dtos.ChapterDto;
import com.example.entities.Book;
import com.example.entities.Chapter;

import java.util.List;
import java.util.UUID;

public interface ChapterService {

    List<Chapter> findAllChapters(UUID bookId);

    ChapterDto getChapter(UUID bookId, int chapterId);

    int count(UUID bookId);
}
