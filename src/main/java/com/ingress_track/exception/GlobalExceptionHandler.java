package com.ingress_track.exception;

import com.ingress_track.util.TransactionLogs;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;


import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex,HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();

        String path = request.getRequestURI();
        String method = request.getMethod();

        ex.getBindingResult().getFieldErrors().forEach((FieldError error) -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        TransactionLogs.log("[Request URI: "+path+"][method: "+method+"]"+"[validation errors: " + errors.toString()+"]");

        return ResponseEntity.badRequest().body(errors);
    }
}
