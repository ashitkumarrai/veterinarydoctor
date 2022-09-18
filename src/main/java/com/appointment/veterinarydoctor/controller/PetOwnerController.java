package com.appointment.veterinarydoctor.controller;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.appointment.veterinarydoctor.entity.Appointment;
import com.appointment.veterinarydoctor.entity.PetOwner;
import com.appointment.veterinarydoctor.exceptionhandler.RecordNotFoundException;
import com.appointment.veterinarydoctor.repository.AppointmentRepository;
import com.appointment.veterinarydoctor.repository.PetOwnerRepository;

@RestController
@RequestMapping("/petOwner")
public class PetOwnerController {
    //patients can send post request for appointments
    @Autowired
    PetOwnerRepository pr;
    @Autowired
    AppointmentRepository ar;
    @GetMapping(value="/{id}")
    public PetOwner getPetOwnerDetails(@PathVariable("id") String id) throws RecordNotFoundException {
        return pr.findById(id).orElseThrow(() -> new RecordNotFoundException("PetOwner is not found in db"));
    }
    
    
    @PostMapping("/appointment")
    public ResponseEntity<Map<String, String>> createAppointment(@Valid @RequestBody Appointment appointment) {
        //PetOwner

        Appointment obj = ar.save(appointment);

        URI loc = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        Map<String, String> map = new HashMap<>();
        map.put("Response", "created in database");
        return ResponseEntity.created(loc).body(map);

    }

    @GetMapping(value="appointment/{id}")
    public List<Appointment> getAppointmentById(@PathVariable("id") String id) throws RecordNotFoundException {
        //it will take petOwnerId and return list of all appointments of that respective petOwner.
        return ar.findByPetOwnerId(id);
    }




}
