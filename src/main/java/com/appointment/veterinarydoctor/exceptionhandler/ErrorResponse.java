package com.appointment.veterinarydoctor.exceptionhandler;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
 
@XmlRootElement(name = "error")
@Data
public class ErrorResponse 
{
  public ErrorResponse(String message, List<String> details,
  String getClass) {
    super();
    this.message = message;
    this.details = details;
    this.getClass = getClass;
  }
 
  public ErrorResponse(String message, String getClass) {
    this.message = message;
    this.getClass = getClass;
    this.details = List.of("custom error response");
  }

  //General error message about nature of error
  private String message;
 
  //Specific errors in API request processing
  private List<String> details;
  private String getClass;
 
  
}