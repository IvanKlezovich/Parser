package com.example.entities;

import com.example.lib.FicBook;
import com.example.lib.Person;
import com.example.lib.Section;
import com.example.lib.Title;
import com.example.lib.parts.sub_parts.Element;
import lombok.SneakyThrows;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Parser {

    private int counter = 1;
    private int index = 0;

    private static final String REGEX = "\\[(\\d+)\\]";

    @SneakyThrows
    public Book getBook(File file) {

        Book book = new Book();

        FicBook ficBook = new FicBook(file);

        List<Chapter> chapters = new ArrayList<>();

        List<Section> sectionList = ficBook.getBody((byte) 0).getSections();

        List<Section> notesList = ficBook.getBody((byte) 1).getSections();

        com.example.lib.parts.Description description = ficBook.getDescription();

        List<Description> descriptions = findAllDescriptions(notesList);

        sectionList.forEach(section ->
                chapters.add(newChapter(section, descriptions)));

        getInfo(book, description);

        book.setIndexes(chapters.size());

        book.setChapters(chapters);

        return book;
    }

    private void getInfo(Book book, com.example.lib.parts.Description description) {

        book.setTitle(description.getTitleInfo().getBookTitle());
        book.setAuthor(getAuthor(description));
    }

    private static String getAuthor(com.example.lib.parts.Description description) {
        Person person = description.getTitleInfo().getAuthors().getFirst();

        if(person != null) {
            if(person.getNickname() != null) {
                return person.getNickname();
            } else {
                return person.getFullName();
            }
        }
        return null;
    }

    private List<Description> findAllNotes(String text, List<Description> descriptions) {

        List<Description> descriptionList = new ArrayList<>();

        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            if (matcher.group(1).equals(Integer.toString(counter))) {
                descriptionList.add(descriptions.get(index));
                counter++;
                index++;
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
