package com.ingress_track.exception;

import com.ingress_track.util.ApiUtil;
import com.ingress_track.util.ResponseMessages;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpServletRequest request) {
        Map<String, String> errors = Map.of("error", "Required request body is missing or malformed");
        return buildErrorResponse(request, HttpStatus.BAD_REQUEST, errors);
    }

    public void handleUnauthorized(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("""
            {
                "status": 401,
                "message": "Request Failed",
                "errors": {
                    "error": "%s"
                }
            }
        """.formatted(message));
    }


    private ResponseEntity<Object> buildErrorResponse(HttpServletRequest request, HttpStatus status, Map<String, String> errors) {
        return ResponseEntity
                .status(status)
                .body(ApiUtil.ResponseHandler(request, status, ResponseMessages.REQ_FAILED_MSG, errors));
    }



}
