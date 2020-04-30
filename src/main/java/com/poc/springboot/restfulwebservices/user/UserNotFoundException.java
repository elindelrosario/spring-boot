package com.poc.springboot.restfulwebservices.user;

// NOTE: Commented since setting of HTTP status is done in CustomizedResponseEntityExceptionHandler
// @ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

  private static final long serialVersionUID = -3273417922409197072L;

  public UserNotFoundException(String message) {
    super(message);
  }

}
