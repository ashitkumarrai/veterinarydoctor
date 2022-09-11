package com.appointment.veterinarydoctor.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.appointment.veterinarydoctor.entity.Appointment;
import com.appointment.veterinarydoctor.entity.Doctor;
import com.appointment.veterinarydoctor.repository.AppointmentRepository;
import com.appointment.veterinarydoctor.repository.DoctorRepository;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private DoctorRepository dr;

public AdminController(DoctorRepository dr) {
    this.dr = dr;
}
@PostMapping(value = "/doctor")
public ResponseEntity<Object>createBook(@Valid @RequestBody Doctor d){
     Doctor drObj = dr.save(d);
     
     URI loc = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(drObj.getId()).toUri();
     Map<String , String > map = new HashMap<>();
    return ResponseEntity.created(loc).body(map.put("Response","created in database"));
    
}
@GetMapping(value="/doctor")
public List<Doctor> getAllBook() {
    return dr.findAll();
}


@GetMapping(value="/doctor/{id}")
public Doctor getBookById(@PathVariable("id")String id) throws EntityNotFoundException {
    return dr.findById(id).orElseThrow(EntityNotFoundException::new);
}

@DeleteMapping("/doctor/{id}")
public ResponseEntity<Map> deleteBook(@PathVariable("id") String id){
    dr.deleteById(id);

    Map<String , String > map = new HashMap<>();
    return new ResponseEntity<>(map,  HttpStatus.OK);

   

    
}
@PutMapping("/doctor/{id}")
public Optional<Doctor> updateBook(@PathVariable("id") String id, @RequestBody Doctor d) throws EntityNotFoundException {

    Optional<Doctor> op = dr.findById(id);
    if (!op.isPresent()) {
        throw new EntityNotFoundException("doctor is not found in db");

    } else if (op.get().getId() == id) {
        if (d.getFullName() != null && !("".equals(d.getFullName()))) {
            op.get().setFullName(d.getFullName());
        }
        if (d.getUser() != null && !("".equals(d.getUser()))) {
            op.get().setUser(d.getUser());
        }

        if (d.getSpecialty() != null && !("".equals(d.getSpecialty()))) {
            op.get().setSpecialty(d.getSpecialty());
        }

        dr.save(op.get());
        return op;

    }
    //here we can throw exception for id cant be changed

    return null;
}

@Autowired
    AppointmentRepository appointmentRepository;

    @GetMapping(value="/appointment")
public List<Appointment> getAlAppointments() {
    return appointmentRepository.findAll();
}
    

}

    
    

