package com.ingress_track.exception;

import com.ingress_track.util.ApiUtil;
import com.ingress_track.util.ResponseMessages;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach((FieldError error) -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(ApiUtil.ResponseHandler(request, HttpStatus.BAD_REQUEST, ResponseMessages.REQ_FAILED_MSG, errors));

    }

}
