package ru.ostrowerkhov.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.DateTimeException;
import java.time.format.DateTimeParseException;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(DateTimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String parseException(DateTimeParseException e) {
        String exceptionInfo = e.getMessage();
        log.error("Can't parse date: {}", e.getParsedString());
        return exceptionInfo;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String incorrectDataException(IllegalArgumentException e) {
        String exceptionInfo = e.getMessage();
        log.error("Incorrect average salary or number of vacation days");
        return exceptionInfo;
    }
}
