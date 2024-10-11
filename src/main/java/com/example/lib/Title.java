package com.example.lib;

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
public class Title {

    private List<P> paragraphs = new ArrayList<>();

    public Title(Node root) {
        NodeList body = root.getChildNodes();
        for (int item = 0; item < body.getLength(); item++) {
            Node node = body.item(item);
            if (node.getNodeName().equals("p")) {
                paragraphs.add(new P(node));
            }
        }
    }
}
