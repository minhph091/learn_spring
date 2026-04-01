package com.minhph091.estateportal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // Bắt exception cho Bean validation
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

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(404);
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setErrors(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    // Bắt exception khi kiểu dữ liệu truyền vào là String trong PathVariable yêu cầu int
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(400);
        errorResponse.setMessage("Invalid value: '" + ex.getValue() + "' for parameter " + ex.getName());
        errorResponse.setErrors(null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateResource(DuplicateResourceException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(409);
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setErrors(null);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
}
