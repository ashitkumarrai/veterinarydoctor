package com.appointment.veterinarydoctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appointment.veterinarydoctor.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment,String> {
    
}
