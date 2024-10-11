package com.example.lib;

import com.example.lib.parts.sub_parts.Element;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Poem extends Element {
    
    private Title title;
    private List<Epigraph> epigraphs;
    private List<Stanza> stanza = new ArrayList<>();
    private String textAuthor;
    private String date;

    Poem(Node node) {
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node paragraph = nodeList.item(i);
            switch (paragraph.getNodeName()) {
                case "text-author":
                    textAuthor = paragraph.getTextContent();
                    break;
                case "title":
                    title = new Title(paragraph);
                    break;
                case "epigraph":
                    if (epigraphs == null) epigraphs = new ArrayList<>();
                    epigraphs.add(new Epigraph(paragraph));
                    break;
                case "date":
                    date = paragraph.getTextContent();
                    break;
                case "stanza":
                    stanza.add(new Stanza(paragraph));
                    break;
                default:
            }
        }
    }

    @Override
    public String getText() {
        ArrayList<Element> list = new ArrayList<>();
        if (title != null) list.addAll(title.getParagraphs());
        for (Stanza stanza1 : stanza) {
            if (stanza1.getTitle() != null) {
                for (Title title1 : stanza1.getTitle()) {
                    if (title1 != null) list.addAll(title1.getParagraphs());
                }
            }
            list.addAll(stanza1.getStanzaList());
        }
        return Element.getText(list, "\n");
    }
}
