package com.appointment.veterinarydoctor.entity;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor

@ToString()
@Entity

public class Doctor {










    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    @GenericGenerator(name = "book_seq", strategy = "com.appointment.veterinarydoctor.entity.StringPrefixedSequenceIdGenerator", parameters = {
            @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
            @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "DOC_"),
            @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })

    private String id;

    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Valid
    private User user;

    @NotBlank(message = "fullName is mandatory")
    private String fullName;

	
	
    private String specialty;
    @Pattern(regexp="^[2-9]{2}\\d{8}$",message= "phone number must have 10 digits")
    private String contact;
    
    @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY, mappedBy = "doctor")
    @JsonBackReference
    @Valid
    private Set<Appointment> appointment;

    public Doctor(@Valid User user, @NotBlank(message = "fullName is mandatory") String fullName, String specialty,
            @Pattern(regexp = "^[2-9]{2}\\d{8}$", message = "phone number must have 10 digits") String contact) {
        this.user = user;
        this.fullName = fullName;
        this.specialty = specialty;
        this.contact = contact;
    }
 



    

  












    
}
