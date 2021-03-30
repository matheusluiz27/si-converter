package com.si.converter.exceptions;

import com.si.converter.exceptions.models.ErrorMessageModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidUnitsException.class)
    public ResponseEntity<Object> handleCompanyDoesNotExistException(InvalidUnitsException e, WebRequest request) {
        ErrorMessageModel errorMessage = new ErrorMessageModel(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
