package com.appointment.veterinarydoctor.controller;




import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.appointment.veterinarydoctor.dto.DoctorDto;
import com.appointment.veterinarydoctor.dto.EmailDetails;
import com.appointment.veterinarydoctor.dto.PetOwnerDto;
import com.appointment.veterinarydoctor.entity.Doctor;
import com.appointment.veterinarydoctor.entity.PetOwner;
import com.appointment.veterinarydoctor.entity.Role;
import com.appointment.veterinarydoctor.entity.User;
import com.appointment.veterinarydoctor.entity.VerificationToken;
import com.appointment.veterinarydoctor.exceptionhandler.RecordNotFoundException;
import com.appointment.veterinarydoctor.repository.DoctorRepository;
import com.appointment.veterinarydoctor.repository.PetOwnerRepository;
import com.appointment.veterinarydoctor.repository.UserRepository;
import com.appointment.veterinarydoctor.repository.VerificationTokenRepository;
import com.appointment.veterinarydoctor.service.EmailService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class OpenController {
    @Autowired
    private PetOwnerRepository pr;
    @Autowired
   private PasswordEncoder passwordEncoder;
   @Autowired
   private DoctorRepository dr;
   @Value("${spring.mail.username}")
   private String sender;
   @Autowired
   private EmailService emailService;
   @Autowired
   private UserRepository userRepository;
   
   @Autowired
   VerificationTokenRepository vtr;
   //anyone can see list of all doctors

   @GetMapping(value="/show/allDoctors")
   public List<Doctor> getAllDr() {
       return dr.findAll();
   }

   @GetMapping(value="/show/doctor/{id}")
public Doctor getDrById(@PathVariable("id")String id) throws RecordNotFoundException {
    return dr.findById(id).orElseThrow(()-> new RecordNotFoundException("doctor is not found in db"));
}


//petOwner can register
    @PostMapping("/register/petOwner")
    public ResponseEntity<EntityModel<PetOwner>> createPetOwner(@Valid @RequestBody PetOwnerDto p) {
        PetOwner petOwner = new PetOwner();

        petOwner.setFullName(p.getFullName());

        User user = new User();
        user.setEmail(p.getUser().getEmail());

        user.setEnabled(p.getUser().isEnabled());

        Set<Role> roles = new HashSet<>();
        roles.add(new Role(103, "PETOWNER"));

        user.setRoles(roles);
        user.setUsername(p.getUser().getUsername());
        user.setPassword(passwordEncoder.encode(p.getUser().getPassword()));
        petOwner.setUser(user);

        log.info(passwordEncoder.encode(p.getUser().getPassword()));
        PetOwner obj = pr.save(petOwner);

        EntityModel<PetOwner> entityModel = EntityModel.of(obj);
        Link link = WebMvcLinkBuilder.linkTo(methodOn(PetOwnerController.class).getPetOwnerDetails(obj.getId()))
                .withRel("this-user");
        entityModel.add(link);

        return new ResponseEntity<>(entityModel, HttpStatus.CREATED);

    }
    
    //doctors can register
    @PostMapping(value = "register/doctor")
    public ResponseEntity<EntityModel<Doctor>> createDr(@Valid @RequestBody DoctorDto d) {

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

        user.setEnabled(d.getUser().isEnabled());

        user.setRoles(d.getUser().getRoles());
        user.setUsername(d.getUser().getUsername());
        user.setPassword(passwordEncoder.encode(d.getUser().getPassword()));
        String token = UUID.randomUUID().toString();
        VerificationToken vt = new VerificationToken(token);

        user.setToken(vt);
        doctor.setUser(user);

        Doctor drObj = dr.save(doctor);
        //for Admin Approval, Email will  be send to admin email i'd 

        EmailDetails emailDetails = new EmailDetails();

        emailDetails.setRecipient(sender);
        emailDetails.setSubject("New Doctor has Registered");
        
        MultiValueMap<String, String> urlParams =new LinkedMultiValueMap<>();
        
       urlParams.add("id", doctor.getId());
          urlParams.add("token", user.getToken().getToken());


        URI loc = ServletUriComponentsBuilder.fromCurrentRequest().path("").queryParams(urlParams)
                .buildAndExpand().toUri();

        String approvalLink = loc.toString();
        emailDetails.setMsgBody(
                "Hi Admin,\n New Doctor has register! \n To Approve and enable the registration: "
                        + "\n\n" + "Full Name : " + drObj.getFullName()
                        + "\n\n" + "Id : " + drObj.getId()
                        + "\n\n" + "Contact: " + drObj.getContact()
                        + "\n\n" + "Speciality: " + drObj.getSpecialty()
                        + "\n\n" + "Email id : " + drObj.getUser().getEmail()
                        + "\n\n To Approve click on this approval link: " + approvalLink);

        emailService.sendSimpleMail(emailDetails);

        //URI loc = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(drObj.getId()).toUri();

        EntityModel<Doctor> entityModel = EntityModel.of(drObj);

        Link link = WebMvcLinkBuilder.linkTo(methodOn(DoctorController.class).getDrById(drObj.getId()))
                .withRel("new-user");

        // Map<String, String> map = new HashMap<>();
        //map.put("Response", "created in database");

        //return ResponseEntity.created(loc).body(map);
        entityModel.add(link);

        return new ResponseEntity<>(entityModel, HttpStatus.CREATED);

    }
    @GetMapping(value="/register/doctor")
    public ResponseEntity<Object> doApprove(@RequestParam("id") String id,@RequestParam("token") String token) {
        User tempUser=null;
         tempUser= userRepository.findByTokenToken(token).orElseThrow(() -> new RecordNotFoundException("token is not found in db"));
        

            Doctor tempDoc = dr.findById(id).orElseThrow(() -> new RecordNotFoundException("Doctor is not found in db"));
            Map<String, String> r = new HashMap<>();
            
            if (tempUser != null && tempDoc.getId().equals(id)) {
                tempUser.setEnabled(true);
                dr.save(tempDoc);
                r.put("Response", "Doctor Registeration Approved.");
                return new ResponseEntity<Object>(r, HttpStatus.OK);
            }
            else {
                r.put("Response", "id & token doesn't matched!");
                return new ResponseEntity<Object>(r, HttpStatus.BAD_REQUEST);
        }

    
        



    }

}
