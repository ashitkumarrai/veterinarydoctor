package com.appointment.veterinarydoctor.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Builder;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@ToString(exclude = { "password" })


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
	
	@Id
	@NotBlank(message = "username is mandatory")
	@Length(min = 3,max=15, message = "must have min 3 chars and max 15 ")
	@Pattern(regexp="([\\w_\\.]){3,15}",message= "must be alpha-numeric [can contains underscore(_)or dot(.) and @]")
	private String username;


	@NotBlank(message = "password is mandatory")
	@Length(min = 8,max=15, message = "must have min 8 chars and max 15 chars.")
	@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,15}$",message= "Minimum eight characters, at least one uppercase letter, one lowercase letter and one number")
	private String password;

	


	@NotBlank(message = "email is mandatory")
	@Email(message = "Email should be valid")
	private String email;
	


	private String role;
	

}
