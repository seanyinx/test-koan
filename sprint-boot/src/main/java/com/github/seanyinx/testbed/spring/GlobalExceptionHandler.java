package com.github.seanyinx.testbed.spring;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  ResponseEntity<String> handleException(Exception ex) {
    return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
  }
}
