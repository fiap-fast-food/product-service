package com.guilherme.fiapfood.infrastructure.exceptions.handler;

import com.guilherme.fiapfood.infrastructure.exceptions.BusinessException;
import com.guilherme.fiapfood.infrastructure.exceptions.ExceptionDTO;
import com.guilherme.fiapfood.infrastructure.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ExceptionDTO> handleBusinessException(BusinessException ex) {

        var exDTO = ExceptionDTO.builder()
                .error(ex.getMessage())
                .timestamp(new Date())
                .status(HttpStatus.BAD_REQUEST.toString())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exDTO);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionDTO> handleProductNotFoundException(ProductNotFoundException ex) {

        var exDTO = ExceptionDTO.builder()
                .error(ex.getMessage())
                .timestamp(new Date())
                .status(HttpStatus.NOT_FOUND.toString())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exDTO);
    }
}
