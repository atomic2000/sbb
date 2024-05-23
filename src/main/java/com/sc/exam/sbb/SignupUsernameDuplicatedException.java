package com.sc.exam.sbb;

public class SignupUsernameDuplicatedException extends RuntimeException {
  public SignupUsernameDuplicatedException(String message) {
    super(message);
  }
}
