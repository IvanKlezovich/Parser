package com.example.controllers;

import com.example.dtos.ChapterDto;
import com.example.entities.Chapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

public interface ChapterController {

    @GetMapping("/chapters/{bookId}")
    List<Chapter> gelAllChapters(
            @PathVariable(name = "bookId") UUID bookId);

    @GetMapping("/chapter/{bookId}/{chapterId}")
    ChapterDto getChapter(
            @PathVariable(name = "bookId") UUID bookId,
            @PathVariable(name = "chapterId") Integer chapterId);
}
