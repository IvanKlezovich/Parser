package com.example.lib;

import com.example.lib.parts.sub_parts.Element;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Annotation extends IdElement {

    private String lang;
    private List<Element> elements = new ArrayList<>();
    
    Annotation(Node node) {
        super(node);
        NamedNodeMap map = node.getAttributes();
        for (int index = 0; index < map.getLength(); index++) {
            Node attr = map.item(index);
            if (attr.getNodeName().equals("xml:lang")) {
                lang = attr.getNodeValue();
            }
        }
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node paragraph = nodeList.item(i);
            switch (paragraph.getNodeName()) {
                case "p":
                    elements.add(new P(paragraph));
                    break;
                case "poem":
                    elements.add(new Poem(paragraph));
                    break;
                case "cite":
                    elements.add(new Cite(paragraph));
                    break;
                case "subtitle":
                    elements.add(new Subtitle(paragraph));
                    break;
                case "empty-line":
                    elements.add(new EmptyLine());
                    break;
                case "table":
                    elements.add(new Table());
                    break;
                default:
            }
        }
    }
}
