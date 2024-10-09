package com.example.save;

import com.example.parser.Title;

import java.util.List;

public class Chapter {
    private Title title;
    private String text;
    private List<Description> descriptionList;

    public Chapter(){}
    public Chapter(Title title, String text, List<Description> descriptionList) {
        this.title = title;
        this.text = text;
        this.descriptionList = descriptionList;
    }
}
