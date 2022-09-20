package com.appointment.veterinarydoctor.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.appointment.veterinarydoctor.dto.DoctorDto;
import com.appointment.veterinarydoctor.entity.Appointment;
import com.appointment.veterinarydoctor.entity.Doctor;
import com.appointment.veterinarydoctor.entity.PetOwner;
import com.appointment.veterinarydoctor.entity.User;
import com.appointment.veterinarydoctor.exceptionhandler.RecordNotFoundException;
import com.appointment.veterinarydoctor.repository.AppointmentRepository;
import com.appointment.veterinarydoctor.repository.DoctorRepository;
import com.appointment.veterinarydoctor.repository.PetOwnerRepository;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private DoctorRepository dr;

    private PetOwnerRepository petOwnerRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

public AdminController(DoctorRepository dr,PetOwnerRepository petOwnerRepository) {
    this.dr = dr;
    this.petOwnerRepository = petOwnerRepository;
}
@PostMapping(value = "/doctor")
public ResponseEntity<Map<String, String>> createDr(@Valid @RequestBody DoctorDto d) {

    Doctor doctor = new Doctor();
  
    doctor.setFullName(d.getFullName());
    doctor.setAppointment(d.getAppointment());
    doctor.setContact(d.getContact());
    doctor.setSpecialty(d.getSpecialty());


    User user = new User();
    user.setEmail(d.getUser().getEmail());

    user.setEnabled(d.getUser().isEnabled());

    user.setRoles(d.getUser().getRoles());
    user.setUsername(d.getUser().getUsername());
    user.setPassword(passwordEncoder.encode(d.getUser().getPassword()));
    doctor.setUser(user);


    
     Doctor drObj = dr.save(doctor);
     
     URI loc = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(drObj.getId()).toUri();
     Map<String, String> map = new HashMap<>();
     map.put("Response", "created in database");
     return ResponseEntity.created(loc).body(map);
    
}
@GetMapping(value="/doctor")
public List<Doctor> getAllDr() {
    return dr.findAll();
}


@GetMapping(value="/doctor/{id}")
public Doctor getBookById(@PathVariable("id")String id) throws RecordNotFoundException {
    return dr.findById(id).orElseThrow(()-> new RecordNotFoundException("doctor is not found in db"));
}

@DeleteMapping("/doctor/{id}")
public ResponseEntity<Map<String, String>> deleteDr(@PathVariable("id") String id) throws RecordNotFoundException {

    Map<String, String> map = new HashMap<>();
    dr.deleteById(id);
    map.put("Response", "deleted from database");
    return new ResponseEntity<>(map, HttpStatus.ACCEPTED);

}


@PutMapping("/doctor/{id}")
public Optional<Doctor> updateDoctor(@PathVariable("id") String id, @Valid @RequestBody Doctor d) throws RecordNotFoundException {

    Optional<Doctor> op = dr.findById(id);
    if (!op.isPresent()) {
        throw new RecordNotFoundException("doctor is not found in db");

    } else {
        if (op.get().getId().equals(id)) {
            if (d.getFullName() != null && !("".equals(d.getFullName()))) {
                op.get().setFullName(d.getFullName());
            }
            if (d.getUser() != null) {
                op.get().setUser(d.getUser());
            }

            if (d.getSpecialty() != null && !("".equals(d.getSpecialty()))) {
                op.get().setSpecialty(d.getSpecialty());
            }

            dr.save(op.get());
           

        }
        return op;
    }
    //here we can throw exception for id cant be changed

 
}

@Autowired
    AppointmentRepository appointmentRepository;

    @GetMapping(value="/appointment")
    public List<Appointment> getAlAppointments() {
        return appointmentRepository.findAll();
    }
    @GetMapping(value="/petOwner")
public List<PetOwner> getAllPetOwners() {
    return petOwnerRepository.findAll();
}
    
@DeleteMapping("/petOwner/{id}")
public ResponseEntity<Map<String, String>> deletePetOnwer(@PathVariable("id") String id) throws RecordNotFoundException {

    Map<String, String> map = new HashMap<>();
    petOwnerRepository.deleteById(id);
    map.put("Response", "deleted from database");
    return new ResponseEntity<>(map, HttpStatus.ACCEPTED);

}
}

    
    

