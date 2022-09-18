package com.appointment.veterinarydoctor.exceptionhandler;



import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
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
          return new ResponseEntity(error, HttpStatus.NOT_FOUND);
        }
       
        @Override
        protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                HttpHeaders headers, HttpStatus status, WebRequest request) {
            List<String> details = new ArrayList<>();
            for (ObjectError error : ex.getBindingResult().getAllErrors()) {
                details.add(error.getDefaultMessage());
            }
            ErrorResponse error = new ErrorResponse("Validation Failed", details,ex.getClass().getName());
            return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
        }

        

        }
        
        
