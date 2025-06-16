package com.example.lib;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.Node;

@Getter
@Setter
@NoArgsConstructor
public class Image {

    private String name;
    private String value;

    public Image(Node node) {
        this.name = node.getAttributes().item(0).getNodeName();
        this.value = node.getAttributes().item(0).getNodeValue();
    }
}
