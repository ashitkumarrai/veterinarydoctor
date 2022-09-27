package com.appointment.veterinarydoctor.entity;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.Valid;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Component
public class User {
	
	@Id
	@JsonProperty(access = Access.WRITE_ONLY)
	private String username;



	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	



	private String email;
	

	
	@JsonProperty(access = Access.WRITE_ONLY)
  
	@ManyToMany(fetch = FetchType.EAGER, cascade =  CascadeType.ALL)
	@Valid
	private Set<Role> roles;
	
	
	@JsonProperty(access = Access.WRITE_ONLY)
    private boolean enabled;


	

	

	


}
