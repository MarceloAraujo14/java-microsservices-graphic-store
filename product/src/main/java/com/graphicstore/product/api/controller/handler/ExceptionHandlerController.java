package com.graphicstore.product.api.controller.handler;

import com.graphicstore.product.api.controller.handler.enums.ExceptionsMessageEnum;
import com.graphicstore.product.api.exception.ProductNotFoundException;
import com.graphicstore.product.domain.service.dto.ExceptionResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Log4j2
public class ExceptionHandlerController {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ExceptionResponse> handleRuntimeException(RuntimeException ex){
        return new ResponseEntity<>(
                new ExceptionResponse(ExceptionsMessageEnum.INTERNAL_ERROR.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> handlerNotFound(RuntimeException ex){
        log.info(ex.getMessage());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);  });
        return ResponseEntity.ok(errors);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleProductException(ProductNotFoundException ex) {
        return new ResponseEntity<>(new ExceptionResponse(ex.getMessage()),HttpStatus.NOT_FOUND);
    }


}
