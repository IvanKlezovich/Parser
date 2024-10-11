package com.example.lib;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class Person {

    private String id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String nickname;
    private List<String> homePages = new ArrayList<>();
    private List<String> emails = new ArrayList<>();

    Person(Node node) {
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node author = nodeList.item(i);
            switch (author.getNodeName()) {
                case "id":
                    id = author.getTextContent();
                    break;
                case "home-page":
                    if (homePages == null) homePages = new ArrayList<>();
                    homePages.add(author.getTextContent());
                    break;
                case "email":
                    if (emails == null) emails = new ArrayList<>();
                    emails.add(author.getTextContent());
                    break;
                case "nickname":
                    nickname = author.getTextContent();
                    break;
                case "first-name":
                    firstName = author.getTextContent();
                    break;
                case "middle-name":
                    middleName = author.getTextContent();
                    break;
                case "last-name":
                    lastName = author.getTextContent();
                    break;
                default:
            }
        }
    }

    public String getFullName() {
        return (firstName == null ? "" : firstName + " ")
                + (middleName == null ? "" : middleName + " ")
                + (lastName == null ? "" : lastName);
    }
}
