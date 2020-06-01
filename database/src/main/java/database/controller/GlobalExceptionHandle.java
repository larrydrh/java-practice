package database.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandle {
    @ResponseStatus(HttpStatus.BAD_REQUEST)  // 409
    @ResponseBody
    @ExceptionHandler(HttpMessageConversionException.class)
    public void handleConflict() {
        // Nothing to do
    }
}
