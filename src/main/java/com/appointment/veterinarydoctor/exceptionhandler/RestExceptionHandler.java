package com.appointment.veterinarydoctor.exceptionhandler;



import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

   

    
       
        @ExceptionHandler(RecordNotFoundException.class)
        public final ResponseEntity<Object> handleUserNotFoundException(RecordNotFoundException ex, WebRequest request) {
          List<String> details = new ArrayList<>();
          details.add(ex.getLocalizedMessage());
          ErrorResponse error = new ErrorResponse("Record Not Found", details,ex.getClass().getName());
          return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
        }
       
        @Override
        protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
          List<String> details = new ArrayList<>();
          ex.getBindingResult().getAllErrors().forEach( error->details.add(error.getDefaultMessage()));
          ErrorResponse error = new ErrorResponse("Validation Failed", details, ex.getClass().getName());
          return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
        }
        


  @ExceptionHandler(UserDisabledException.class)
  public final ResponseEntity<Object> handleUserDisabled(UserDisabledException ex, WebRequest request) {

    ErrorResponse error = new ErrorResponse("User is disable contact admin to enable", ex.getClass().getName());
    
    return new ResponseEntity(error, HttpStatus.UNAUTHORIZED);
  }
        
  @ExceptionHandler(UserBadCredentialsException.class)
  public final ResponseEntity<Object> handleUserBadCredentials(UserBadCredentialsException ex, WebRequest request) {

    ErrorResponse error = new ErrorResponse("Bad Credentials", ex.getClass().getName());
    return new ResponseEntity<Object>(error, HttpStatus.UNAUTHORIZED);
  }
  


  
  @ExceptionHandler({ ConstraintViolationException.class })
  public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
    List<String> errors = new ArrayList<String>();
    errors.add(ex.getCause().toString());

    ErrorResponse apiError = new ErrorResponse(ex.getMessage(), errors,
        ex.getClass().getName());
    return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);

    //return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
  }


  

        }
        
        
