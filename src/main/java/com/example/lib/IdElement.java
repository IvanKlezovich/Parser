package com.example.lib;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

@NoArgsConstructor
@Getter
@Setter
public class IdElement {

    private String id;

    public IdElement(Node node) {
        NamedNodeMap map = node.getAttributes();
        for (int index = 0; index < map.getLength(); index++) {
            Node attr = map.item(index);
            if (attr.getNodeName().equals("id")) {
                id = attr.getNodeValue();
            }
        }
    }
}
