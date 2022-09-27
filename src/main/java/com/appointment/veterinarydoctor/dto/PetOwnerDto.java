package com.appointment.veterinarydoctor.dto;


import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class PetOwnerDto {

    public PetOwnerDto(@NotBlank(message = "fullName is mandatory") String fullName, @Valid UserDto user) {
        this.fullName = fullName;
        this.user = user;
        
    }

    private String id;

    @NotBlank(message = "fullName is mandatory")
    private String fullName;

   
    @JsonBackReference
    @Valid
    private UserDto user;

    

  

}

