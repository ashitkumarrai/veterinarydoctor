package com.appointment.veterinarydoctor.entity;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Component
@Builder
public class User {
	
	@Id
	@JsonProperty(access = Access.WRITE_ONLY)
	@Length(min = 3,max=15, message = "must have min 3 chars and max 15 ")
	@Pattern(regexp = "([\\w_\\.]){3,15}", message = "must be alpha-numeric [can contains underscore(_)or dot(.) and @]")
	private String username;


	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	


	@Email(message = "Email should be valid")
	private String email;
	
	
	

	
	@JsonProperty(access = Access.WRITE_ONLY)
  
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@Valid
	private Set<Role> roles;
	
	
	@JsonProperty(access = Access.WRITE_ONLY)
	//for extra email event 
	private boolean enabled;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private VerificationToken token;


	

	

	


}
