package com.example.service.impl;

import com.example.dtos.ChapterDto;
import com.example.entities.Book;
import com.example.entities.Chapter;
import com.example.service.BookService;
import com.example.service.ChapterService;
import com.example.utils.ChapterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChapterServiceImpl implements ChapterService {

    private final BookService bookService;
    private final ChapterMapper chapterMapper;

    @Override
    public List<Chapter> findAllChapters(UUID bookId) {
        return getBook(bookId)
                .getChapters();
    }

    @Override
    public ChapterDto getChapter(UUID bookId, int chapterId) {
        return chapterMapper.toChapterDto(
                getBook(bookId)
                .getChapters().get(chapterId));
    }

    @Override
    public int count(UUID bookId) {
        return getBook(bookId)
                .getChapters().size();
    }

    private Book getBook(UUID bookId) {
        return bookService.getBookById(bookId);
    }
}
