package com.example.lib;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

@NoArgsConstructor
@Getter
@Setter
public class Date {

    private String value;
    private String dateString;

    Date(Node node) {
        NamedNodeMap map = node.getAttributes();
        for (int index = 0; index < map.getLength(); index++) {
            Node attr = map.item(index);
            if (attr.getNodeName().equals("value")) {
                value = attr.getNodeValue();
            }
        }
        dateString = node.getTextContent();
    }
}
