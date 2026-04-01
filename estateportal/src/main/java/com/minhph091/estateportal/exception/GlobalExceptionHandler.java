package com.minhph091.estateportal.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(400);
        errorResponse.setMessage("Bad Request");
        List<FieldError> fieldErrorList = ex.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrorList) {
            Map<String, String> values = new HashMap<>();
            values.put(error.getField(), error.getDefaultMessage());
            errorResponse.setErrors(values);
        }
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
