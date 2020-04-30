package com.poc.springboot.restfulwebservices.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.poc.springboot.restfulwebservices.user.UserNotFoundException;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(UserNotFoundException.class)
  public final ResponseEntity<Object> handleUserNotFoundException(Exception ex,
      WebRequest request) {

    ExceptionResponse exceptionResponse =
        new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
    ExceptionResponse exceptionResponse =
        new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {

    List<String> messages = new ArrayList<>();

    if (ex.getBindingResult().hasErrors()) {
      List<FieldError> errors = ex.getBindingResult().getFieldErrors();

      for (FieldError e : errors) {
        StringBuilder builder = new StringBuilder("{");
        builder.append("field: '").append(e.getField());
        builder.append("', message: '").append(e.getDefaultMessage());
        builder.append("'}");
        messages.add(builder.toString());
      }
    }

    ExceptionResponse exceptionResponse =
        new ExceptionResponse(new Date(), "Validation Error", messages.toString());

    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }

}
