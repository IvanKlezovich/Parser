package com.example.parser;

import org.w3c.dom.Node;

public class Image {

    protected String name;
    protected String value;

    public Image(Node node) {
        this.name = node.getAttributes().item(0).getNodeName();
        this.value = node.getAttributes().item(0).getNodeValue();
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public Image() {
        super();
    }
}
