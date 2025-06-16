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
public class Cite extends Element {

    private String id;
    private String lang;
    private List<Element> elements = new ArrayList<>();
    private List<TextAuthor> textAuthor = new ArrayList<>();

    Cite(Node node) {
        NamedNodeMap attrs = node.getAttributes();
        for (int index = 0; index < attrs.getLength(); index++) {
            Node attr = attrs.item(index);
            if (attr.getNodeName().equals("id")) {
                id = attr.getNodeValue();
            }
            if (attr.getNodeName().equals("xml:lang")) {
                lang = attr.getNodeValue();
            }
        }

        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            addElement(nodeList.item(i));
        }
    }

    public List<Element> getElements() {
        return elements == null ? new ArrayList<>() : elements;
    }

    @Override
    public String getText() {
        ArrayList<Element> list = new ArrayList<>(getElements());
        if (textAuthor != null) list.addAll(textAuthor);
        return Element.getText(list, "\n");
    }

    private void addElement(Node paragraph){
        switch (paragraph.getNodeName()) {
            case "text-author":
                textAuthor.add(new TextAuthor(paragraph));
                break;
            case "poem":
                elements.add(new Poem(paragraph));
                break;
            case "subtitle":
                elements.add(new Subtitle(paragraph));
                break;
            case "p":
                elements.add(new P(paragraph));
                break;
            case "empty-line":
                elements.add(new EmptyLine());
                break;
            default:
        }
    }
}
