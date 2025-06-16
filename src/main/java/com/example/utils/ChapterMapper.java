package com.example.utils;

import com.example.dtos.ChapterDto;
import com.example.entities.Chapter;
import com.example.lib.P;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ChapterMapper {

    public ChapterDto toChapterDto(Chapter chapter) {
        ChapterDto chapterDto = new ChapterDto();
        chapterDto.setText(chapter.getText());
        chapterDto.setDescriptionList(chapter.getDescriptionList());
        chapterDto.setTitle(
                chapter.getTitle().getParagraphs().stream()
                        .map(P::getText)
                        .collect(Collectors.joining("\n"))
        );
        return chapterDto;
    }
}
