package com.appointment.veterinarydoctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appointment.veterinarydoctor.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient,String>{
    
}
