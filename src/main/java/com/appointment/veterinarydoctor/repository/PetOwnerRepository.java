package com.appointment.veterinarydoctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appointment.veterinarydoctor.entity.PetOwner;

public interface PetOwnerRepository extends JpaRepository<PetOwner,String>{
    
}
