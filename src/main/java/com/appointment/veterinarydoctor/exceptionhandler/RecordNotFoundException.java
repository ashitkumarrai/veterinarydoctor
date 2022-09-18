package com.appointment.veterinarydoctor.exceptionhandler;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
 
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends EntityNotFoundException 
{
  public RecordNotFoundException(String exception) {
    super(exception);
  }
}
