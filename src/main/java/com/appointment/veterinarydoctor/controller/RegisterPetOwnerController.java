package com.appointment.veterinarydoctor.controller;




import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.appointment.veterinarydoctor.dto.PetOwnerDto;
import com.appointment.veterinarydoctor.entity.PetOwner;
import com.appointment.veterinarydoctor.entity.Role;
import com.appointment.veterinarydoctor.entity.User;
import com.appointment.veterinarydoctor.repository.PetOwnerRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class RegisterPetOwnerController {
    @Autowired
    private PetOwnerRepository pr;
    @Autowired
   private PasswordEncoder passwordEncoder;

    @PostMapping("/register/petOwner")
    public ResponseEntity<Map<String, String>> createPetOwner(@Valid @RequestBody PetOwnerDto p) {
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
         
          URI loc = ServletUriComponentsBuilder.fromCurrentContextPath().path("/petOwner/{id}").buildAndExpand(obj.getId()).toUri();
        
         Map<String, String> map = new HashMap<>();
         map.put("Response", "created in database");
         return ResponseEntity.created(loc).body(map);
        
    }
}
