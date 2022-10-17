package com.appointment.veterinarydoctor.service;

// Importing required classes

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.appointment.veterinarydoctor.dto.EmailDetails;

// Annotation
@Service
// Class
// Implementing EmailService interface
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    private Map<String, String> map = new HashMap<>();
    @Value("${spring.mail.username}")
    private String sender;

    // Method 1
    // To send a simple email

    public ResponseEntity<Map<String, String>> sendSimpleMail(EmailDetails details) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        try {

            // Creating a simple mail message

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());

            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            // Sending the mail
            javaMailSender.send(mailMessage);
            String status = "Mail Sent Successfully...";
            map.put("Response", status);
            return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
        }

        catch (Exception e) {

            // Display message when exception occurred
            String status = "Error while sending mail!!!";
            map.put("Response", status);
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);

        }

    }

    // Method 2
    // To send an email with attachment
    public ResponseEntity<Map<String, String>>

            sendMailWithAttachment(EmailDetails details) {
        // Creating a mime message
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {

            // Setting multipart as true for attachments to
            // be send
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(
                    details.getSubject());

            // Adding the attachment
            FileSystemResource file = new FileSystemResource(
                    new File(details.getAttachment()));

            mimeMessageHelper.addAttachment(
                    file.getFilename(), file);

            // Sending the mail
            javaMailSender.send(mimeMessage);
            String status = "Mail Sent Successfully...";
            map.put("Response", status);
            return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
        }

        // Catch block to handle MessagingException
        catch (MessagingException e) {

            // Display message when exception occurred
            String status = "Error while sending mail!!!";
            map.put("Response", status);
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);

        }
    }
}
