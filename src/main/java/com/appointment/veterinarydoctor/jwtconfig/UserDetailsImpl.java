package com.appointment.veterinarydoctor.jwtconfig;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.appointment.veterinarydoctor.entity.Role;
import com.appointment.veterinarydoctor.entity.User;

@Component
public class UserDetailsImpl implements UserDetails{
	

    private User user;



    public UserDetailsImpl(User user) 
    {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
         
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
         
        return authorities;
    }

    @Override
    public String getPassword() {
        
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
      
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
     
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
     
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
     
        return true;
    }

  
    @Override
    public boolean isEnabled() {
        // it should return user isEnable......(user.isenabled())
        return true;
    }
    
}

