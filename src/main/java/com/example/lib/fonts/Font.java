package com.example.lib.fonts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Font {

    private final int startIndex;
    private final int finishIndex;

    protected Font(String emphasis, String p) {
        startIndex = p.indexOf(emphasis);
        finishIndex = startIndex + emphasis.length();
    }
}
