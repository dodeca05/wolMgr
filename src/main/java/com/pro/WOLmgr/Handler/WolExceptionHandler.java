package com.pro.WOLmgr.Handler;

import jdk.jshell.spi.ExecutionControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WolExceptionHandler {

    @ExceptionHandler(ExecutionControl.NotImplementedException.class)
    public ResponseEntity<String> handleNotImplementedException(ExecutionControl.NotImplementedException ex) {
        return new ResponseEntity<>("NotImplementedException: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
