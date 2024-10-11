package com.example.lib;

import com.example.lib.fonts.Emphasis;
import com.example.lib.fonts.StrikeThrough;
import com.example.lib.fonts.Strong;
import com.example.lib.parts.sub_parts.Element;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class P extends Element {
    
    private List<Image> images = new ArrayList<>();
    private List<Emphasis> emphasis = new ArrayList<>();
    private List<Strong> strong = new ArrayList<>();
    private List<StrikeThrough> strikeThrough = new ArrayList<>();
    
//    TODO
//    Для нижних индексов <sub>, а для верхних индексов <sup>
//    Программный код - <code>
//    <subtitle>* * *</subtitle>

//  <cite>
//  <p>Время - деньги.<p>
//  <text-author>Бенджамин Франклин</text-author>
//  </cite>

    //  <p>Об этом вы можете прочитать <a l:href="#n1">здесь</a>.</p>
//  <p>text<a l:href="#n_2" type="note">[2]</a>

    public P(Image image) {
        super();
        if (images == null) images = new ArrayList<>();
        images.add(image);
    }

    public P(Node p) {
        super(p);
        NodeList nodeList = p.getChildNodes();
        for (int index = 0; index < nodeList.getLength(); index++) {
            Node node = nodeList.item(index);
            switch (nodeList.item(index).getNodeName()) {
                case "image":
                    if (images == null) images = new ArrayList<>();
                    images.add(new Image(node));
                    break;
                case "strikethrough":
                    if (strikeThrough == null) strikeThrough = new ArrayList<>();
                    strikeThrough.add(new StrikeThrough(node.getTextContent(), p.getTextContent()));
                    break;
                case "strong":
                    if (strong == null) strong = new ArrayList<>();
                    strong.add(new Strong(node.getTextContent(), p.getTextContent()));
                    break;
                case "emphasis", "subtitle":
                    if (emphasis == null) emphasis = new ArrayList<>();
                    emphasis.add(new Emphasis(node.getTextContent(), p.getTextContent()));
                    break;
                default:
            }
        }
    }

    public P(String p) {
        super(p);
    }
}
