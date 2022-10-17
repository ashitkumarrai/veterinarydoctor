package com.appointment.veterinarydoctor.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

// Java Program to Illustrate EmailDetails Class

 
// Importing required classes
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
// Annotations
@Data
@AllArgsConstructor
@NoArgsConstructor
 
// Class
@Component
public class EmailDetails {
 
    // Class data members
    @NotBlank(message = "email is mandatory")
	@Email(message = "Email should be valid")
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}