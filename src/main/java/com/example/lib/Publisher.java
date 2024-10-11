package com.example.lib;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

@Getter
@Setter
@NoArgsConstructor
public class Publisher extends Person{

    private String lang;

    public Publisher(Node node) {
        super(node);
        NamedNodeMap map = node.getAttributes();
        for (int index = 0; index < map.getLength(); index++) {
            Node attr = map.item(index);
            if (attr.getNodeName().equals("lang")) {
                setId(attr.getNodeValue());
            }
        }
    }
}
