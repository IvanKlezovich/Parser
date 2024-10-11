package com.example.lib.parts;

import com.example.lib.IdElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

@Getter
@Setter
@NoArgsConstructor
public class Binary extends IdElement {
    private String contentType;
    private String binaryString;

    public Binary(Node node) {
        super(node);
        binaryString = node.getTextContent();
        NamedNodeMap map = node.getAttributes();
        for (int index = 0; index < map.getLength(); index++) {
            Node attr = map.item(index);
            if (attr.getNodeName().equals("content-type")) {
                contentType = attr.getNodeValue();
            }
        }
    }
}
