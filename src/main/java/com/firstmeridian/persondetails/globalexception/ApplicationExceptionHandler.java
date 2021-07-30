package com.firstmeridian.persondetails.globalexception;

import com.firstmeridian.persondetails.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {PersonException.class})
    public ResponseEntity<ApiResponse<String>> handlePersonExceptions(PersonException exception, WebRequest webRequest) {
        ApiResponse<String> response = new ApiResponse<>("Error occurred internally", exception.getMessage(), false);
        response.setMessage("Not found");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {AddressException.class})
    public ResponseEntity<ApiResponse<String>> handlePersonExceptions(AddressException exception, WebRequest webRequest) {
        ApiResponse<String> response = new ApiResponse<>("Error occurred internally", exception.getMessage(), false);
        response.setMessage("Not found");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
