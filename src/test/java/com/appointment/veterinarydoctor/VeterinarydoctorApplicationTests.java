package com.appointment.veterinarydoctor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@DataJpaTest
class VeterinarydoctorApplicationTests {

	
	void createToken() 
		{
			/*RestTemplate template = new RestTemplate();
			Book b=new Book();
			b.setName("ammullu");
			b.setId(20);

		    ResponseEntity<String> result = template.postForEntity("http://localhost:8080/Book/",b, String.class);
		     
		    //Verify request succeed				
		    assertEquals(201,result.getStatusCodeValue());
		    System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii"+result.getStatusCodeValue());*/
		    }
			
}
