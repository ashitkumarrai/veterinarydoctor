package com.appointment.veterinarydoctor.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.appointment.veterinarydoctor.entity.Appointment;
import com.appointment.veterinarydoctor.entity.Doctor;
import com.appointment.veterinarydoctor.entity.PetOwner;
import com.appointment.veterinarydoctor.entity.User;





@DataJpaTest
public class AppointmentRepositoryTest {

    @Autowired
     UserRepository userRepository;

    @Autowired
     DoctorRepository doctorRepository;

    @Autowired
     AppointmentRepository appointmentRepository;

    @Autowired 
     PetOwnerRepository petOwnerRepository;




    

    @Test
    void testFindByDoctor() throws ParseException {
        List<Appointment> lstOfAppointment = new ArrayList<>();
       
        User  user = User.builder()

                .username("doctor")
                .email("doctor@gmail.com")
                .password("Test@123")
                .enabled(true)
               
                .build();


               

        User user2 = User.builder()

        .username("testPetOwnerName")
        .email("testpetOnwer@gmail.com")
        .password("Test@123")
        .enabled(true)
       
        .build();

        PetOwner petOwner = new PetOwner("testPetOwnerName", user2);
       
        petOwnerRepository.save(petOwner);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");      
        //Parsing the given String to Date object
        Date date = formatter.parse("22-12-2022"); 

       
        
        
        
        

        
       

        
        Doctor doctor = Doctor.builder().fullName("manas").user(user).specialty("Surgeon").contact("9112589973")
                .build();
        // doctor.getAppointment().add(appointment);
        doctorRepository.save(doctor);
        
       Appointment appointment = Appointment.builder().petName("testDog").petAge(2).petOwner(petOwner).date(date)
       .description("Test health").contact("9834567890").doctor(doctor).build();
         
        appointmentRepository
                .save(appointment);
                lstOfAppointment.add(appointment);

        assertEquals(lstOfAppointment,appointmentRepository.findByDoctor(doctorRepository.findByFullName("manas")));

        

    }

    @Test
    void testFindByPetOwnerId() throws ParseException {
      
        User  user = User.builder()

                .username("doctor2")
                .email("doctor@gmail.com")
                .password("Test@123")
                .enabled(true)
               
                .build();


                Doctor doctor = new Doctor(user, "Dr. M.L Khanna", "Surgeon", "9112589972");
        
        doctorRepository.save(doctor);

        User user2 = User.builder()

        .username("testPetOwnerName2")
        .email("testpetOnwer@gmail.com")
        .password("Test@123")
        .enabled(true)
     
        .build();

        PetOwner petOwner = new PetOwner("testPetOwnerName2", user2);
       
        petOwnerRepository.save(petOwner);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");      
        //Parsing the given String to Date object
        Date date = formatter.parse("23-12-2022"); 

        Appointment appointment = Appointment.builder().petName("testDog2").petAge(2).petOwner(petOwner).date(date)
                .description("Test health").contact("9834567890").build();
        
        
        
        

        appointmentRepository
                .save(appointment);
        List<Appointment> lstOfAppointment = new ArrayList<>();
        lstOfAppointment.add(appointment);

        assertEquals(lstOfAppointment,appointmentRepository.findByPetOwnerId(petOwnerRepository.findByFullName("testPetOwnerName2").getId()));

    }
}
