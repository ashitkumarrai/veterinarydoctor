package com.appointment.veterinarydoctor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appointment.veterinarydoctor.entity.Appointment;
import com.appointment.veterinarydoctor.entity.Doctor;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {
    
    public List<Appointment> findByDoctor(Doctor doctor);
    
}
