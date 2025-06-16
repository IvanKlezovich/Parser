package com.example.lib;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

@Getter
@Setter
@NoArgsConstructor
public class Sequence {
    
    private String name;
    private String number;

    Sequence(Node node) {
        NamedNodeMap map = node.getAttributes();
        for (int index = 0; index < map.getLength(); index++) {
            Node attr = map.item(index);
            if (attr.getNodeName().equals("name")) {
                name = attr.getNodeValue();
            } else if (attr.getNodeName().equals("number")) {
                number = attr.getNodeValue();
            }
        }
    }
}
