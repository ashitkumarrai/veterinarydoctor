package com.appointment.veterinarydoctor.jwtconfig;


import java.io.Serializable;
import lombok.Data;
import lombok.AllArgsConstructor;



@Data
@AllArgsConstructor
public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;


}
