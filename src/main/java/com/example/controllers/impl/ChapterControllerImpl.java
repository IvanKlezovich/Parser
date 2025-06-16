package com.example.controllers.impl;

import com.example.controllers.ChapterController;
import com.example.dtos.ChapterDto;
import com.example.entities.Chapter;
import com.example.service.ChapterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chapter")
public class ChapterControllerImpl implements ChapterController {

    private final ChapterService chapterService;

    public List<Chapter> gelAllChapters(UUID bookId) {
        List<Chapter> chapters = chapterService.findAllChapters(bookId);
        return chapters;
    }

    public ChapterDto getChapter(UUID bookId, Integer chapterId) {
        ChapterDto chapter = chapterService.getChapter(bookId, chapterId);
        return chapter;
    }
}
