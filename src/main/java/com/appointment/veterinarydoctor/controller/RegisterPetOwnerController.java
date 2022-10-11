package com.appointment.veterinarydoctor.controller;




import java.util.HashSet;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.appointment.veterinarydoctor.dto.PetOwnerDto;
import com.appointment.veterinarydoctor.entity.PetOwner;
import com.appointment.veterinarydoctor.entity.Role;
import com.appointment.veterinarydoctor.entity.User;
import com.appointment.veterinarydoctor.repository.PetOwnerRepository;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class RegisterPetOwnerController {
    @Autowired
    private PetOwnerRepository pr;
    @Autowired
   private PasswordEncoder passwordEncoder;

    @PostMapping("/register/petOwner")
    public ResponseEntity<EntityModel<PetOwner>> createPetOwner(@Valid @RequestBody PetOwnerDto p) {
        PetOwner petOwner = new PetOwner();
       
        petOwner.setFullName(p.getFullName());

        
        User user = new User();
        user.setEmail(p.getUser().getEmail());

        user.setEnabled(p.getUser().isEnabled());
       
        Set<Role> roles = new HashSet<>();
         roles.add(new Role(103,"PETOWNER"));

        user.setRoles(roles);
        user.setUsername(p.getUser().getUsername());
        user.setPassword(passwordEncoder.encode(p.getUser().getPassword()));
        petOwner.setUser(user);

       
        log.info(passwordEncoder.encode(p.getUser().getPassword()));
         PetOwner obj = pr.save(petOwner);
         
        
        
   
         EntityModel<PetOwner> entityModel = EntityModel.of(obj);
         Link link = WebMvcLinkBuilder.linkTo(methodOn(PetOwnerController.class).getPetOwnerDetails(obj.getId())).withRel("this-user");
         entityModel.add(link);
  
         return new ResponseEntity<>(entityModel,HttpStatus.CREATED);
        
    }
}
