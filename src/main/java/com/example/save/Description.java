package com.example.save;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Description {
    private String number;
    private String description;

    public Description(String number, String description) {
        this.number = number;
        this.description = description;
    }

    public Description() {
    }
}
