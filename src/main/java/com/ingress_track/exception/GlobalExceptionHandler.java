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

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return buildErrorResponse(request, HttpStatus.BAD_REQUEST, errors);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex, HttpServletRequest request) {
        Map<String, String> errors = Map.of("error", ex.getMessage());
        return buildErrorResponse(request, HttpStatus.BAD_REQUEST, errors);
    }


    private ResponseEntity<Object> buildErrorResponse(HttpServletRequest request, HttpStatus status, Map<String, String> errors) {
        return ResponseEntity
                .status(status)
                .body(ApiUtil.ResponseHandler(request, status, ResponseMessages.REQ_FAILED_MSG, errors));
    }
}
