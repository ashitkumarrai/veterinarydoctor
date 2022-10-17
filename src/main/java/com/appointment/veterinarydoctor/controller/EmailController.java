package com.appointment.veterinarydoctor.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.appointment.veterinarydoctor.dto.EmailDetails;
import com.appointment.veterinarydoctor.service.EmailService;


@RestController
public class EmailController {
    @Autowired private EmailService emailService;
    
    // Sending a simple Email
    @PostMapping("/admin/sendMail")
    public ResponseEntity<Map<String,String>> sendMail(@Valid @RequestBody EmailDetails details)
    {
        //System.out.println(details)
        return  emailService.sendSimpleMail(details);
       
        
    }
 
    // Sending email with attachment
    @PostMapping("admin/sendMailWithAttachment")
    public ResponseEntity<Map<String,String>>  sendMailWithAttachment(
        @RequestBody EmailDetails details)
    {
        return  emailService.sendSimpleMail(details);
        
     
    }
}
