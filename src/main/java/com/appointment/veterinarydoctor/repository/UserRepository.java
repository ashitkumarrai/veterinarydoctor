package com.appointment.veterinarydoctor.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appointment.veterinarydoctor.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User,String> {
	
	public User findByUsername(String username);

	public Optional<User> findByTokenToken(String token);
    
}