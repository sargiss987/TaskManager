package com.example.taskmanager.exception;

public class UserPasswordIncorrectException extends RuntimeException {

  public UserPasswordIncorrectException(String message) {
    super(message);
  }
}
