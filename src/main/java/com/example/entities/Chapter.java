package com.example.entities;

import com.example.lib.Title;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Chapter {
    private Title title;
    private String text;
    private List<Description> descriptionList;
}
