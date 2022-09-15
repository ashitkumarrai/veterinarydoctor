package com.appointment.veterinarydoctor.model;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;

import com.appointment.veterinarydoctor.entity.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class AppointmentForm {

    private String petName;
    private int petAge;
 
    
    @Future(message = "date not valid(it should be of future")
    //@JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date date;


    @NotBlank(message = "description is mandatory")
    private String description;

    @NotBlank(message = "phone number is mandatory")
    private String ownerContact;

    @NotBlank(message = "doctorName number is mandatory")
    private String doctorName;

    @NotBlank(message = "doctorName number is mandatory")
    private String specialty;



    
}
