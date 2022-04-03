package com.example.demo.exception;

import lombok.Getter;
@Getter
public enum ErrorCode {

    NO_SUCH_TEACHER_FOUND(2000, "Nie znaleziono nauczyciela o podanym name"),
    NO_SUCH_STUDENT_FOUND(1000, "Nie znaleziono studenta o podanym name");

    private final int code;
    private final String description;

    ErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
