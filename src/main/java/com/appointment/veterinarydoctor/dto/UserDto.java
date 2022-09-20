package com.appointment.veterinarydoctor.dto;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import com.appointment.veterinarydoctor.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder



@Data
@NoArgsConstructor
@AllArgsConstructor

@Component
public class UserDto {
	

	@NotBlank(message = "username is mandatory")
	@Length(min = 3,max=15, message = "must have min 3 chars and max 15 ")
	@Pattern(regexp = "([\\w_\\.]){3,15}", message = "must be alpha-numeric [can contains underscore(_)or dot(.) and @]")

	private String username;


	@NotBlank(message = "password is mandatory")
	@Length(min = 8,max=15, message = "must have min 8 chars and max 15 chars.")
	@Pattern(regexp ="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$", message = "Password must have Min. eight characters, at least one uppercase letter, one lowercase letter and one number and alteast one special characters[@#$%^&*]")

	private String password;

	


	@NotBlank(message = "email is mandatory")
	@Email(message = "Email should be valid")
	private String email;
	


    //@Column(columnDefinition = "varchar(255) default 'ROLE_STUDENT'")

	@Valid
	private Set<Role> roles;
	
    private boolean enabled;


	

	

	


}

