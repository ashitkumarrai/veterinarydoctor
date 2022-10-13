package com.appointment.veterinarydoctor.service;

import com.appointment.veterinarydoctor.dto.EmailDetails;

public interface EmailService {
    String sendSimpleMail(EmailDetails details);
 
    // Method
    // To send an email with attachment
    String sendMailWithAttachment(EmailDetails details);
}
