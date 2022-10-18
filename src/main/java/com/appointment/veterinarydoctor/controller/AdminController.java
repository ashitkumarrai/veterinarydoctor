package com.appointment.veterinarydoctor.controller;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appointment.veterinarydoctor.dto.DoctorDto;
import com.appointment.veterinarydoctor.entity.Appointment;
import com.appointment.veterinarydoctor.entity.Doctor;
import com.appointment.veterinarydoctor.entity.PetOwner;
import com.appointment.veterinarydoctor.entity.Role;
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

    public AdminController(DoctorRepository dr, PetOwnerRepository petOwnerRepository) {
        this.dr = dr;
        this.petOwnerRepository = petOwnerRepository;
    }



@PostMapping(value = "/doctor")
public ResponseEntity<Object> createDr(@Valid @RequestBody DoctorDto d)   {

    Doctor doctor = new Doctor();
    Set<Role> hs = new HashSet<>();
    hs.add(new Role(102, "DOCTOR"));
    d.getUser().setRoles(hs);
    doctor.setFullName(d.getFullName());
    doctor.setAppointment(d.getAppointment());
    doctor.setContact(d.getContact());
    doctor.setSpecialty(d.getSpecialty());


    User user = new User();
    user.setEmail(d.getUser().getEmail());

  

    user.setRoles(d.getUser().getRoles());
    user.setUsername(d.getUser().getUsername());
    user.setPassword(passwordEncoder.encode(d.getUser().getPassword()));
    user.setEnabled(true);
    doctor.setUser(user);


    
    Doctor drObj;
     
    
        drObj = dr.save(doctor);
    
       
 
        

 
     
     //URI loc = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(drObj.getId()).toUri();

     EntityModel<Doctor> entityModel = EntityModel.of(drObj);

     Link link = WebMvcLinkBuilder.linkTo(methodOn(OpenController.class).getDrById(drObj.getId())).withRel("new-user");


    // Map<String, String> map = new HashMap<>();
     //map.put("Response", "created in database");

     //return ResponseEntity.created(loc).body(map);
     entityModel.add(link);
  
     return new ResponseEntity<>(entityModel,HttpStatus.CREATED);
    
}




@DeleteMapping("/doctor/{id}")
public ResponseEntity<Map<String, String>> deleteDr(@PathVariable("id") String id) throws Exception {

    Map<String, String> map = new HashMap<>();
    try{dr.deleteById(id);}
    catch (EmptyResultDataAccessException e) {
        throw new RecordNotFoundException(id);
    }
    map.put("Response", "deleted from database");
    return new ResponseEntity<>(map, HttpStatus.ACCEPTED);

}




 


@Autowired
    AppointmentRepository appointmentRepository;

@GetMapping(value = "/appointment")
    public List<Appointment> getAlAppointments() {
        return appointmentRepository.findAll();
    }
    @GetMapping(value="/petOwner")
public List<PetOwner> getAllPetOwners() {
    return petOwnerRepository.findAll();
}
    
@DeleteMapping("/petOwner/{id}")
public ResponseEntity<Map<String, String>> deletePetOnwer(@PathVariable("id") String id)
        throws RecordNotFoundException {

    Map<String, String> map = new HashMap<>();
    try {
        petOwnerRepository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
        throw new RecordNotFoundException(id);
    }

    map.put("Response", "deleted from database");
    return new ResponseEntity<>(map, HttpStatus.ACCEPTED);

}



}

    
    

