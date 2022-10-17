package com.appointment.veterinarydoctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appointment.veterinarydoctor.entity.VerificationToken;




@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    
}
