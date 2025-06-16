package com.example.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {

    private UUID id;
    private String title;
    private String author;
    private Integer indexes;
    private List<Chapter> chapters;

}
