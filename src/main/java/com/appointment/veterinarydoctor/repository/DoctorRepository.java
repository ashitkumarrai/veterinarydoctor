package com.appointment.veterinarydoctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appointment.veterinarydoctor.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor,String>{
    
}
