package com.app.comparetool.exceptionhandler;

import com.app.comparetool.dto.ErrorResponse;
import com.app.comparetool.exceptionhandler.CustomException.DatabaseNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(DatabaseNotFoundException.class)
    public ResponseEntity<?> handleDatabaseNotFoundException(DatabaseNotFoundException e){
        log.info("In Exception handler - {}", e);
        return new ResponseEntity<>(new ErrorResponse("Runtime Error", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException e) {
        log.info("In Exception handler - {}", e);
        return new ResponseEntity<>(new ErrorResponse("Runtime Error", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        log.info("In Exception handler - {}", e);
        return new ResponseEntity<>(new ErrorResponse("Runtime Error", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
