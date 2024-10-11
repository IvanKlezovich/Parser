package com.example.lib.parts;

import com.example.lib.Epigraph;
import com.example.lib.Image;
import com.example.lib.Section;
import com.example.lib.Title;
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
public class Body {
    private String lang;
    private String name;
    private Title title;
    private Image image;
    private List<Section> sections = new ArrayList<>();
    private List<Epigraph> epigraphs = new ArrayList<>();

    public Body(Node body) {
        NamedNodeMap attrs = body.getAttributes();
        for (int index = 0; index < attrs.getLength(); index++) {
            Node attr = attrs.item(index);
            if (attr.getNodeName().equals("name")) {
                name = attr.getNodeValue();
            }
            if (attr.getNodeName().equals("xml:lang")) {
                lang = attr.getNodeValue();
            }
        }
        NodeList map = body.getChildNodes();
        for (int index = 0; index < map.getLength(); index++) {
            Node node = map.item(index);
            switch (node.getNodeName()) {
                case "section":
                    sections.add(new Section(node));
                    break;
                case "title":
                    title = new Title(node);
                    break;
                case "name":
                    name = node.getTextContent();
                    break;
                case "image":
                    image = new Image(node);
                    break;
                case "epigraph":
                    epigraphs.add(new Epigraph(node));
                    break;
                default:
            }
        }
    }
}
