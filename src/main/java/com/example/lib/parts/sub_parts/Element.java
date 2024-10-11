package com.example.lib.parts.sub_parts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.Node;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Element {
    protected String text;

    public Element(Node p) {
        text = p.getTextContent();
    }

    public Element(String p) {
        text = p;
    }

    public static String getText(List<Element> list, String divider) {
        StringBuilder text = new StringBuilder();
        for (Element p : list) {
            text.append(p.getText()).append(divider);
        }
        if (text.length() <= divider.length()) return "";
        return text.substring(0, text.length() - divider.length()).trim();
    }
}
