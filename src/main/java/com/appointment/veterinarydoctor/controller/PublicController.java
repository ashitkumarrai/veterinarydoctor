package com.appointment.veterinarydoctor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appointment.veterinarydoctor.entity.Appointment;
import com.appointment.veterinarydoctor.repository.AppointmentRepository;

@RestController
@RequestMapping("/public")
public class PublicController {

@Autowired
    AppointmentRepository appointmentRepository;

    @GetMapping(value="/appointment")
public List<Appointment> getAlAppointments() {
    return appointmentRepository.findAll();
}
}
