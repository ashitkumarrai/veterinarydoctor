package com.appointment.veterinarydoctor.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.appointment.veterinarydoctor.exceptionhandler.UserBadCredentialsException;
import com.appointment.veterinarydoctor.exceptionhandler.UserDisabledException;
import com.appointment.veterinarydoctor.jwtconfig.JwtRequest;
import com.appointment.veterinarydoctor.jwtconfig.JwtResponse;
import com.appointment.veterinarydoctor.jwtconfig.JwtTokenUtil;
import com.appointment.veterinarydoctor.jwtconfig.UserDetailsServiceImpl;



@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@PostMapping("/auth/login/token")
	public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new UserDisabledException("USER_DISABLED");
		} catch (BadCredentialsException e) {
			throw new UserBadCredentialsException("INVALID_CREDENTIALS");
		}
	}
}
