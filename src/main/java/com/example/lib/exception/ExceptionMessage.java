package com.example.lib.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionMessage {
    SOMETHING_WENT_WRONG("Ошибка конвертации");

    private final String message;
}
