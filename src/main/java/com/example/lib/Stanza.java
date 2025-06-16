package com.example.lib;

import com.example.lib.parts.sub_parts.Element;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Stanza {
    private List<Title> title = new ArrayList<>();
    private List<Element> stanzaList = new ArrayList<>();

    Stanza(Node node) {
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node paragraph = nodeList.item(i);
            switch (paragraph.getNodeName()) {
                case "title":
                    if (title == null) title = new ArrayList<>();
                    title.add(new Title(paragraph));
                    break;
                case "subtitle":
                    if (stanzaList == null) stanzaList = new ArrayList<>();
                    stanzaList.add(new Subtitle(paragraph));
                    break;
                case "v":
                    if (stanzaList == null) stanzaList = new ArrayList<>();
                    stanzaList.add(new V(paragraph));
                    break;
                default:
            }
        }
    }
}
