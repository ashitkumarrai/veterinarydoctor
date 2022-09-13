/*  package com.appointment.veterinarydoctor.customdataloader;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.annotation.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.appointment.veterinarydoctor.entity.Appointment;
import com.appointment.veterinarydoctor.entity.Doctor;
import com.appointment.veterinarydoctor.entity.Patient;
import com.appointment.veterinarydoctor.entity.User;
import com.appointment.veterinarydoctor.repository.AppointmentRepository;
import com.appointment.veterinarydoctor.repository.DoctorRepository;
import com.appointment.veterinarydoctor.repository.PatientRepository;

@ManagedBean
public class DataLoader implements CommandLineRunner {
    @Autowired
    PatientRepository patientRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Override
    public void run(String... args) throws Exception {
        Patient patient = new Patient("Ashish Kumar Rai",
                new User("ashishkumarrai", "GodLikeSpeed@6", "ashishkumarrai@gmail.com", "ROLE_NORMAL"));


        patientRepository.save(patient);

    
        Doctor doctor = new Doctor(
                new User("ramkumar", "GodLikeSpeed@8", "ramkumar@gmail.com", "ROLE_DOCTOR"), "Dr. Ram Kumar",
                "Radiology", "9876543215");
        doctorRepository.save(doctor);
        String date_string = "26-10-2022";
        //Instantiating the SimpleDateFormat class
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");      
        //Parsing the given String to Date object
        Date date = formatter.parse(date_string);      
        System.out.println("Date value: "+date);

        
        Appointment appointment1 = new Appointment("rocky", 3, date, "mandatory monthly checkup",
                "9876543216");
                appointment1.setDoctor(doctor);

                appointmentRepository.save(appointment1);


        
                Appointment appointment2 = new Appointment("rambha", 3, date,  "mandatory yearly checkup",
                "9976543246");
                appointment2.setDoctor(doctor);

        appointmentRepository.save(appointment2);
        

        
        


     



        


        
    }
    
}
 */