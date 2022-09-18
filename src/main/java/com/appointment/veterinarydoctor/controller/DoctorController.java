package com.appointment.veterinarydoctor.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appointment.veterinarydoctor.entity.Appointment;
import com.appointment.veterinarydoctor.entity.Doctor;
import com.appointment.veterinarydoctor.exceptionhandler.RecordNotFoundException;
import com.appointment.veterinarydoctor.repository.AppointmentRepository;
import com.appointment.veterinarydoctor.repository.DoctorRepository;



@RestController
@RequestMapping("/doctor")
public class DoctorController {

    //doctor can only see appointments
    @Autowired
    AppointmentRepository ar;

    @Autowired
    DoctorRepository dr;



    @GetMapping(value = "appointments/{id}")
public List<Appointment> getAppointmentById(@PathVariable("id")String  id) throws RecordNotFoundException {
   //teacher details and list of students and course details
   Optional<Doctor> d = dr.findById(id);
   Doctor doc=null;
   
   if (d.isPresent()) {
     doc = d.get();
   }

    return ar.findByDoctor(doc);
}

    @GetMapping(value="/{id}")
    public Doctor getBookById(@PathVariable("id")String id) throws RecordNotFoundException {
        return dr.findById(id).orElseThrow(()-> new RecordNotFoundException("doctor is not found in db"));
    }



}
    

