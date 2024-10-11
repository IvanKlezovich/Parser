package com.example.lib;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class TitleInfo {
    private List<String> genre = new ArrayList<>();

//  TODO http://www.fictionbook.org/index.php/Жанры_FictionBook_2.1

    private List<String> keywords = new ArrayList<>();
    private String bookTitle;
    private String date;
    private String lang;
    private String srcLang;
    private List<Person> authors = new ArrayList<>();
    private List<Person> translators = new ArrayList<>();
    private Annotation annotation;
    private List<Image> coverPage = new ArrayList<>();
    private Sequence sequence;

    public TitleInfo(Document document) {
        NodeList description = document.getElementsByTagName("title-info");
        for (int item = 0; item < description.getLength(); item++) {
            NodeList map = description.item(item).getChildNodes();
            for (int index = 0; index < map.getLength(); index++) {
                Node node = map.item(index);
                switch (node.getNodeName()) {
                    case "sequence":
                        sequence = new Sequence(node);
                        break;
                    case "coverpage":
                        NodeList images = node.getChildNodes();
                        for (int image = 0; image < images.getLength(); image++) {
                            if (images.item(image).getNodeName().equals("image")) {
                                coverPage.add(new Image(images.item(image)));
                            }
                        }
                        break;
                    case "elements":
                        this.annotation = new Annotation(node);
                        break;
                    case "date":
                        date = node.getTextContent();
                        break;
                    case "author":
                        authors.add(new Person(node));
                        break;
                    case "translator":
                        translators.add(new Person(node));
                        break;
                    case "keywords":
                        keywords.add(node.getTextContent());
                        break;
                    case "genre":
                        genre.add(node.getTextContent());
                        break;
                    case "book-title":
                        bookTitle = node.getTextContent();
                        break;
                    case "lang":
                        lang = node.getTextContent();
                        break;
                    case "src-lang":
                        srcLang = node.getTextContent();
                        break;
                    default:
                }
            }
        }
    }
}
