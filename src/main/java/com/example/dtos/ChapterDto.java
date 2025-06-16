package com.example.dtos;

import com.example.entities.Description;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChapterDto {
    private String title;
    private String text;
    private List<Description> descriptionList;
}
