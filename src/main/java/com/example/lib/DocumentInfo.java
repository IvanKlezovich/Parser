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
public class DocumentInfo {

    private List<Person> authors = new ArrayList<>();
    private List<Person> publishers = new ArrayList<>();
    private String programUsed;
    private String srcUrl;
    private String srcOcr;
    private String email;
    private String id;
    private String version;
    private History history;
    private Date date;
    
    public DocumentInfo(Document document) {
        NodeList description = document.getElementsByTagName("document-info");
        for (int item = 0; item < description.getLength(); item++) {
            NodeList map = description.item(item).getChildNodes();
            for (int index = 0; index < map.getLength(); index++) {
                Node node = map.item(index);
                switch (node.getNodeName()) {
                    case "author":
                        authors.add(new Person(node));
                        break;
                    case "publisher":
                        if (publishers == null) publishers = new ArrayList<>();
                        publishers.add(new Person(node));
                        break;
                    case "program-used":
                        programUsed = node.getTextContent();
                        break;
                    case "email":
                        email = node.getTextContent();
                        break;
                    case "src-url":
                        srcUrl = node.getTextContent();
                        break;
                    case "src-ocr":
                        srcOcr = node.getTextContent();
                        break;
                    case "id":
                        id = node.getTextContent();
                        break;
                    case "version":
                        version = node.getTextContent();
                        break;
                    case "history":
                        this.history = new History(node);
                        break;
                    case "date":
                        this.date = new Date(node);
                        break;
                    default:
                }
            }
        }
    }
}
