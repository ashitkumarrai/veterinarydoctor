package com.appointment.veterinarydoctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appointment.veterinarydoctor.entity.PetOwner;
@Repository
public interface PetOwnerRepository extends JpaRepository<PetOwner, String> {
    
    PetOwner   findByFullName(String fullName);
    
}
