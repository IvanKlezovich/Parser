package com.example.lib;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Getter
@Setter
@NoArgsConstructor
public class PublishInfo {

    protected String bookName;
    protected String city;
    protected String year;
    protected String publisher;
    protected String isbn;
    protected Sequence sequence;

    public PublishInfo(Document document) {
        NodeList description = document.getElementsByTagName("publish-info");
        for (int item = 0; item < description.getLength(); item++) {
            NodeList map = description.item(item).getChildNodes();
            for (int index = 0; index < map.getLength(); index++) {
                Node node = map.item(index);
                switch (node.getNodeName()) {
                    case "book-name":
                        bookName = node.getTextContent();
                        break;
                    case "city":
                        city = node.getTextContent();
                        break;
                    case "year":
                        year = node.getTextContent();
                        break;
                    case "isbn":
                        isbn = node.getTextContent();
                        break;
                    case "publisher":
                        publisher = node.getTextContent();
                        break;
                    case "sequence":
                        sequence = new Sequence(node);
                        break;
                    default:
                }
            }
        }
    }
}
