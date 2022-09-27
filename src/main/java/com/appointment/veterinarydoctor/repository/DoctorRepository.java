package com.appointment.veterinarydoctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appointment.veterinarydoctor.entity.Doctor;
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, String> {
    

      Doctor findByFullName(String fullName);
    
}
