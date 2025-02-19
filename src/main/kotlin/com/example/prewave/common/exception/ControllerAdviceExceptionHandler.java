package com.example.prewave.common.exception;

import com.example.prewave.edge.exception.EdgeNotFoundException;
import com.example.prewave.node.exception.TreeNodeNotFoundException;
import org.jooq.exception.IntegrityConstraintViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdviceExceptionHandler {

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateKeyException(DuplicateKeyException ex) {
        return buildErrorResponse(HttpStatus.CONFLICT, "Object already exists: " + ex.getMessage());
    }

    @ExceptionHandler(IntegrityConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> handleIntegrityConstraintViolationException(IntegrityConstraintViolationException ex) {
        return buildErrorResponse(HttpStatus.CONFLICT, "Unique constraint violation: " + ex.getMessage());
    }

    @ExceptionHandler(EdgeNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleEdgeNotFoundException(EdgeNotFoundException ex) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(TreeNodeNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleTreeNodeNotFoundException(TreeNodeNotFoundException ex) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                fieldErrors.put(error.getField(), error.getDefaultMessage())
        );
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");
        body.put("message", "Validation failed");
        body.put("errors", fieldErrors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred: " + ex.getMessage());
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse(HttpStatus status, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        return new ResponseEntity<>(body, status);
    }
}