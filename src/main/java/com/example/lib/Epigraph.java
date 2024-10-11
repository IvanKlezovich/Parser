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


@NoArgsConstructor
@Getter
@Setter
public class Epigraph extends IdElement{

    private List<Element> elements = new ArrayList<>();
    private List<TextAuthor> textAuthor = new ArrayList<>();

    public Epigraph(Node root) {
        NamedNodeMap map = root.getAttributes();
        for (int index = 0; index < map.getLength(); index++) {
            Node attr = map.item(index);
            if (attr.getNodeName().equals("id")) {
                setId(attr.getNodeValue());
            }
        }
        NodeList body = root.getChildNodes();
        for (int item = 0; item < body.getLength(); item++) {
            Node node = body.item(item);
            switch (node.getNodeName()) {
                case "text-author":
                    if (textAuthor == null) textAuthor = new ArrayList<>();
                    textAuthor.add(new TextAuthor(node));
                    break;
                case "poem":
                    elements.add(new Poem(node));
                    break;
                case "cite":
                    elements.add(new Cite(node));
                    break;
                case "p":
                    elements.add(new P(node));
                    break;
                default:
            }
        }
    }
}
