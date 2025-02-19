package com.example.prewave.edge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, code = HttpStatus.NOT_FOUND)
public class EdgeNotFoundException extends RuntimeException {
    public EdgeNotFoundException(String message) {
        super(message);
    }
}