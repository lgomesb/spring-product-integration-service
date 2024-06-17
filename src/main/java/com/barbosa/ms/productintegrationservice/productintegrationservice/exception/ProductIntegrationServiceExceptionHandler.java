package com.barbosa.ms.productintegrationservice.productintegrationservice.exception;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.logging.Logger;

@ControllerAdvice
public class ProductIntegrationServiceExceptionHandler {

    Logger logger = Logger.getLogger(ProductIntegrationServiceExceptionHandler.class.getName());

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardError> constraintViolationError(ConstraintViolationException e, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .messege(e.getMessage())
                .path(request.getRequestURI())
                .build();
        logger.info("#".repeat(10) + "ERROR HANDLER");
        e.getConstraintViolations().forEach(c -> logger.info(c.getMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<StandardError> productNotFoundError(ProductNotFoundException e, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .messege(e.getMessage())
                .path(request.getRequestURI())
                .build();
        logger.info("#".repeat(10) + "ERROR HANDLER");
        logger.info(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(ProductOrderServiceException.class)
    public ResponseEntity<StandardError> productOrderServiceError(ProductOrderServiceException e, HttpServletRequest request) {
        StandardError error = StandardError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .messege(e.getMessage())
                .path(request.getRequestURI())
                .build();
        logger.info("#".repeat(10) + "ERROR HANDLER");
        logger.info(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validationError(MethodArgumentNotValidException e, HttpServletRequest request) {

        ValidationError error = new ValidationError(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Validation error",
                null,
                request.getRequestURI()
        );

        e.getBindingResult()
                .getFieldErrors()
                .forEach(f -> error.addError(f.getField(), f.getDefaultMessage()));


        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }

    @ExceptionHandler( FeignException.class )
    public ResponseEntity<StandardError> feignException(FeignException e, HttpServletRequest request) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String stJson = new String(e.responseBody().get().array());
        StandardError error = mapper.readValue(stJson, StandardError.class);
        logger.info("#".repeat(10) + "ERROR HANDLER");
        logger.info(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
    