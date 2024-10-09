package com.example.parser;

import com.example.parser.parts.sub_parts.Element;
import com.example.save.Book;
import com.example.save.Chapter;
import com.example.save.Description;
import lombok.SneakyThrows;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Parser {

    private Integer counter = 1;

    @SneakyThrows
    public Book getBook(File file) {

        Book book = new Book();

        FicBook ficBook = new FicBook(file);

        List<Chapter> chapters = new ArrayList<>();

        List<Section> sectionList = ficBook.getBody((byte) 0).getSections();

        List<Section> notesList = ficBook.getBody((byte) 1).getSections();

        com.example.parser.parts.Description description = ficBook.getDescription();

        List<Description> descriptions = findAllDescriptions(notesList);

        sectionList.forEach(section ->
                chapters.add(newChapter(section, descriptions)));

        getInfo(book, description);

        book.setChapters(chapters);

        return book;
    }

    private void getInfo(Book book, com.example.parser.parts.Description description) {
        book.setTitle(description.getTitleInfo().bookTitle);
        book.setAuthor(getAuthor(description));
    }

    private static String getAuthor(com.example.parser.parts.Description description) {
        Person person = description.getTitleInfo().authors.getFirst();

        if(person != null) {
            if(person.nickname != null) {
                return person.nickname;
            } else if (person.firstName != null || person.middleName != null || person.lastName != null || person.lastName != null) {
                if(person.middleName != null) {
                    return person.firstName + " " + person.middleName + " " + person.lastName;
                }else{
                    if(person.firstName != null) {
                        return person.firstName + " " + person.lastName;
                    }else{
                        return person.lastName;
                    }
                }
            }
        }
        return null;
    }

    private List<Description> findAllNotes(String text, List<Description> descriptions) {

        List<Description> descriptionList = new ArrayList<>();

        Pattern pattern = Pattern.compile("\\[(\\d+)\\]");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            if (matcher.group(1).equals(counter.toString())) {
                descriptionList.add(descriptions.get(--counter));
                counter += 2;
            } else {
                for (Description description : descriptions) {
                    if (matcher.group(1).equals(description.getNumber())) {
                        descriptionList.add(description);
                    }
                }
            }
        }

        return descriptionList;
    }

    private Chapter newChapter(Section section, List<Description> descriptions) {
        String text = section.getElements().stream()
                .map(Element::getText)
                .collect(Collectors.joining("\n"));

        return new Chapter(section.getTitle(),
                text,
                findAllNotes(text, descriptions));
    }

    private List<Description> findAllDescriptions(List<Section> notesList) {

        List<Description> descriptionList = new ArrayList<>();

        notesList.forEach(note -> {
            Title title = note.getTitle();
            descriptionList.add(new Description(
                    title.getParagraphs().getFirst().getText(),
                    note.getElements().stream()
                            .map(Element::getText)
                            .collect(Collectors.joining("\n"))));
        });

        return descriptionList;
    }
}
