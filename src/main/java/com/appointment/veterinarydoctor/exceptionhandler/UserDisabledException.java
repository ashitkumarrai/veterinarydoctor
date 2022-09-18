package com.appointment.veterinarydoctor.exceptionhandler;

public class UserDisabledException extends RuntimeException{
    


  public UserDisabledException(String exception) {
    super(exception);
  }
}
