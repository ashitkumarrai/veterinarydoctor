package com.appointment.veterinarydoctor.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor

@ToString()
@Entity

public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    @GenericGenerator(name = "book_seq", strategy = "com.appointment.veterinarydoctor.entity.StringPrefixedSequenceIdGenerator", parameters = {
            @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
            @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "APPOINT_"),
            @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })

    private String id;

    private String petName;
    private int petAge;

   
    @Future(message = "date not valid(it should be of future")
    //@JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date date;


    @NotBlank(message = "description is mandatory")
    private String description;

    @NotBlank(message = "phone number is mandatory")
    private String contact;

    @ManyToOne(cascade = CascadeType.ALL)
   @JsonManagedReference
    private Doctor doctor;
 
  

    


    public Appointment(String petName, int petAge,
            @Future(message = "date not valid(it should be of future") Date date,
            @NotBlank(message = "description is mandatory") String description,
            @NotBlank(message = "phone number is mandatory") String contact) {
        this.petName = petName;
        this.petAge = petAge;
        this.date = date;
        this.description = description;
        this.contact = contact;
    }




    
}
