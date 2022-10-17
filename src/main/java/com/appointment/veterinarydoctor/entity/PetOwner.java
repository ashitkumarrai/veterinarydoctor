package com.appointment.veterinarydoctor.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;



import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor


@Entity
public class PetOwner {

    public PetOwner( String fullName,  User user) {
        this.fullName = fullName;
        this.user = user;
        
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "petOwner_seq")
    @GenericGenerator(name = "petOwner_seq", strategy = "com.appointment.veterinarydoctor.entity.StringPrefixedSequenceIdGenerator", parameters = {
            @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
            @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "PAT_"),
            @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })

    private String id;

    
    private String fullName;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference
  
    private User user;

  

}
