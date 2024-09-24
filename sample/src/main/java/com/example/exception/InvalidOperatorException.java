package com.example.exception;

public class InvalidOperatorException extends RuntimeException {
    public InvalidOperatorException() {
        super("Invlid operator, you need to choose one of (+,-,*,/)");
    }
}
