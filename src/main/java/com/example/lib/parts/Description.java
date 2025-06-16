package com.example.lib.parts;

import com.example.lib.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Description {

    private TitleInfo titleInfo;
    private SrcTitleInfo srcTitleInfo;
    private DocumentInfo documentInfo;
    private PublishInfo publishInfo;
    private List<CustomInfo> customInfo;

    public Description(Document doc) {
        titleInfo = new TitleInfo(doc);
        srcTitleInfo = new SrcTitleInfo(doc);
        documentInfo = new DocumentInfo(doc);
        publishInfo = new PublishInfo(doc);
    }
}
