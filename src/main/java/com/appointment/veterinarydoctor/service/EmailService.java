package com.appointment.veterinarydoctor.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.appointment.veterinarydoctor.dto.EmailDetails;

public interface EmailService {
    ResponseEntity<Map<String,String>>  sendSimpleMail(EmailDetails details);
 
    // Method
    // To send an email with attachment
    ResponseEntity<Map<String,String>>  sendMailWithAttachment(EmailDetails details);
}
