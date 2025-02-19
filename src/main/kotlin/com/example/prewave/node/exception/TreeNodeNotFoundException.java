package com.example.prewave.node.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, code = HttpStatus.NOT_FOUND)
public class TreeNodeNotFoundException extends RuntimeException {
    public TreeNodeNotFoundException(String message) {
        super(message);
    }
}