package com.example.lib.parts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.Node;

@Getter
@Setter
@NoArgsConstructor
public class Xmlns {
    private String name;
    private String value;

    public Xmlns(Node node) {
        this.name = node.getNodeName();
        this.value = node.getNodeValue();
    }
}
