package com.appointment.veterinarydoctor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appointment.veterinarydoctor.entity.Appointment;
import com.appointment.veterinarydoctor.entity.Doctor;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, String> {
    
    public List<Appointment> findByDoctor(Doctor doctor);
    //@Query(value = "SELECT * FROM  appointment app WHERE app.pet_owner_id = ?1",nativeQuery = true)
    public List<Appointment> findByPetOwnerId(String id);
    
}
