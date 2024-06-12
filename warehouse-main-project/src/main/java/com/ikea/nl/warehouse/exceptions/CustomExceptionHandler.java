package com.ikea.nl.warehouse.exceptions;

import java.time.LocalDateTime;

import com.ikea.nl.warehouse.dto.CustomErrorResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> productNotFoundException(Exception ex, WebRequest request) {
        CustomErrorResponse cer = new CustomErrorResponse();
        cer.setTimestamp(LocalDateTime.now());
        cer.setError(ex.getMessage());
        cer.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(cer, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotInStockException.class)
    public ResponseEntity<CustomErrorResponse> productNotInStockException(Exception ex, WebRequest request) {
        CustomErrorResponse cer = new CustomErrorResponse();
        cer.setTimestamp(LocalDateTime.now());
        cer.setError(ex.getMessage());
        cer.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(cer, HttpStatus.NOT_FOUND);
    }

}
