package com.appointment.veterinarydoctor.repository;





import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.appointment.veterinarydoctor.entity.Role;
import com.appointment.veterinarydoctor.entity.User;


//@RunWith(SpringRunner.class)
// @SpringBootTest

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Test
    void testFindByUsername() {
        Set<Role> hs = new HashSet<>();
        hs.add(new Role(101, "ADMIN"));
        User user = User.builder()

                .username("testsubjet")
                .email("Test@gmail.com")
                .password("Test@123")
                .enabled(true)
                .roles(hs)
                .build();
        
        
        
       
        userRepository.save(user);
        assertThat(user).isEqualTo(userRepository.findByUsername(user.getUsername()));
        


        

    }
}
