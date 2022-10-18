package com.appointment.veterinarydoctor.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appointment.veterinarydoctor.entity.Appointment;
import com.appointment.veterinarydoctor.entity.Doctor;
import com.appointment.veterinarydoctor.exceptionhandler.RecordNotFoundException;
import com.appointment.veterinarydoctor.jwtconfig.JwtTokenUtil;
import com.appointment.veterinarydoctor.repository.AppointmentRepository;
import com.appointment.veterinarydoctor.repository.DoctorRepository;

import io.jsonwebtoken.ExpiredJwtException;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    // doctor can only see appointments
    @Autowired
    AppointmentRepository ar;

    @Autowired
    DoctorRepository dr;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping(value = "appointments/{id}")
    public List<Appointment> getAppointmentById(@PathVariable("id") String id) throws RecordNotFoundException {

        Optional<Doctor> d = dr.findById(id);
        Doctor doc = null;

        if (d.isPresent()) {
            doc = d.get();
        }

        return ar.findByDoctor(doc);
    }

    @GetMapping(value = "/{id}")
    public Doctor getDrById(@PathVariable("id") String id) throws RecordNotFoundException {
        return dr.findById(id).orElseThrow(() -> new RecordNotFoundException("doctor is not found in db"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDoctor(@RequestHeader("Authorization") String bearerToken,
            @PathVariable("id") String id, @Valid @RequestBody Doctor d)
            throws RecordNotFoundException {

        Doctor op = dr.findById(id).orElseThrow(() -> new RecordNotFoundException("doctor is not found in db"));

        Map<String, String> ex = new HashMap<>();

        // Doctor can update only his particular details
        String username = null;
        String jwtToken = null;
        // JWT Token is in the form "Bearer token". Remove Bearer word and get
        // only the Token
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            jwtToken = bearerToken.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
                if (!username.equals(op.getUser().getUsername())) {
                    ex.put("Response", "only logged in Doctor can update his self details");
                    return new ResponseEntity<>(ex, HttpStatus.NOT_ACCEPTABLE);

                }
            } catch (IllegalArgumentException e) {

            } catch (ExpiredJwtException e) {

            }
        }

        // blank check and null checks
        // Doctor can only update his full-name User and Speciality and contact

        if (op.getId().equals(id)) {
            if (d.getFullName() != null && !("".equals(d.getFullName()))) {
                op.setFullName(d.getFullName());
            }

            if (d.getSpecialty() != null && !("".equals(d.getSpecialty()))) {
                op.setSpecialty(d.getSpecialty());
            }
            if (d.getContact() != null && !("".equals(d.getContact()))) {
                op.setContact(d.getContact());
            }

            

            if (d.getUser() != null & d.getUser().getPassword() != null) {
                op.getUser().setPassword(d.getUser().getPassword());
            }
            if (d.getUser() != null & d.getUser().getEmail() != null) {
                op.getUser().setEmail(d.getUser().getEmail());
            }

            if (d.getSpecialty() != null && !("".equals(d.getSpecialty()))) {
                op.setSpecialty(d.getSpecialty());
            }
            Doctor tempD = dr.save(op);
            return new ResponseEntity<>(tempD, HttpStatus.ACCEPTED);

        }

        else {

            ex.put("Response", "PathVariable id doesn't matched with requestbody id.");
            return new ResponseEntity<>(ex, HttpStatus.NOT_ACCEPTABLE);
        }

    }

}
