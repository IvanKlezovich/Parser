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
public class Section extends IdElement {

    private Image image;
    private Annotation annotation;
    private List<Epigraph> epigraphs = new ArrayList<>();
    private List<Section> sections = new ArrayList<>();
    private List<Element> elements = new ArrayList<>();
    private Title title;

    public Section(Node root) {
        super(root);
        NodeList body = root.getChildNodes();
        for (int item = 0; item < body.getLength(); item++) {
            Node node = body.item(item);
            switch (node.getNodeName()) {
                case "title":
                    title = new Title(node);
                    break;
                case "elements":
                    annotation = new Annotation(node);
                    break;
                case "image":
                    elements.add(new P(new Image(node)));
                    break;
                case "epigraph":
                    epigraphs.add(new Epigraph(node));
                    break;
                case "section":
                    sections.add(new Section(node));
                    break;
                case "poem":
                    elements.add(new Poem(node));
                    break;
                case "subtitle":
                    elements.add(new Subtitle(node));
                    break;
                case "p":
                    elements.add(new P(node));
                    break;
                case "empty-line":
                    elements.add(new EmptyLine());
                    break;
                case "cite":
                    elements.add(new Cite(node));
                    break;
                default:
            }
        }
    }

    public String getTitleString(String innerDivider, String outerDivider) {
        if (title == null) return "";
        StringBuilder builder = new StringBuilder();
        ArrayList<Element> list = new ArrayList<>(title.getParagraphs());
        builder.append(Element.getText(list, innerDivider)).append(outerDivider);
        return builder.substring(0, builder.length() - outerDivider.length());
    }

    @Override
    public String toString() {
        String data = getTitleString(". ", "\n");
        if (!getElements().isEmpty()) {
            data += " p: " + elements.size();
        }
        if (!getSections().isEmpty()) {
            data += " section: " + sections.size();
        }
        return data.trim();
    }
}
