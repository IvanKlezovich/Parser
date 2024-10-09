package com.example.parser.parts;

import com.example.parser.IdElement;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class Binary extends IdElement {
    protected String contentType;
    protected String binary;

    public Binary() {
    }

    public Binary(Node node) {
        super(node);
        binary = node.getTextContent();
        NamedNodeMap map = node.getAttributes();
        for (int index = 0; index < map.getLength(); index++) {
            Node attr = map.item(index);
            switch (attr.getNodeName()) {
                case "content-type":
                    contentType = attr.getNodeValue();
                    break;
            }
        }
    }

    public String getContentType() {
        return contentType;
    }

    public String getBinary() {
        return binary;
    }

    @Override
    public String getId() {
        return super.getId();
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setBinary(String binary) {
        this.binary = binary;
    }
}
