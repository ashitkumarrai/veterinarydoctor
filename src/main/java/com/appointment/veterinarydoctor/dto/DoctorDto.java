package com.appointment.veterinarydoctor.dto;


import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.appointment.veterinarydoctor.entity.Appointment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class DoctorDto {







    private String id;

    
   
    @Valid
    private UserDto user;

    @NotBlank(message = "fullName is mandatory")
    private String fullName;

	
	
    private String specialty;
    @Pattern(regexp="^[2-9]{2}\\d{8}$",message= "phone number must have 10 digits")
    private String contact;
    

    @Valid
    private Set<Appointment> appointment;

    public DoctorDto(@Valid UserDto user, @NotBlank(message = "fullName is mandatory") String fullName, String specialty,
            @Pattern(regexp = "^[2-9]{2}\\d{8}$", message = "phone number must have 10 digits") String contact) {
        this.user = user;
        this.fullName = fullName;
        this.specialty = specialty;
        this.contact = contact;
    }
 



    

  












    
}
