// package com.appointment.veterinarydoctor.customdataloader;

// import java.time.LocalDate;

// import javax.annotation.ManagedBean;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;

// import com.appointment.veterinarydoctor.entity.Appointment;
// import com.appointment.veterinarydoctor.entity.Doctor;
// import com.appointment.veterinarydoctor.entity.Patient;
// import com.appointment.veterinarydoctor.entity.User;
// import com.appointment.veterinarydoctor.repository.AppointmentRepository;
// import com.appointment.veterinarydoctor.repository.DoctorRepository;
// import com.appointment.veterinarydoctor.repository.PatientRepository;

// @ManagedBean
// public class DataLoader implements CommandLineRunner {
//     @Autowired
//     PatientRepository patientRepository;

//     @Autowired
//     DoctorRepository doctorRepository;

//     @Autowired
//     AppointmentRepository appointmentRepository;

//     @Override
//     public void run(String... args) throws Exception {
//         Patient patient = new Patient("Ashish Kumar Rai",
//                 new User("ashishkumarrai", "GodLikeSpeed6", "ashishkumarrai@gmail.com", "ROLE_NORMAL"));


//         patientRepository.save(patient);

    
//         Doctor doctor = new Doctor(
//                 new User("ramkumar", "GodLikeSpeed8", "ramkumar@gmail.com", "ROLE_DOCTOR"), "Dr. Ram Kumar",
//                 "Radiology", "9876543215");
//                 doctorRepository.save(doctor);
        
//         Appointment appointment1 = new Appointment("rocky", 3, LocalDate.of(2023, 1, 8), "mandatory monthly checkup",
//                 "9876543216");
//                 appointment1.setDoctor(doctor);

//                 appointmentRepository.save(appointment1);


        
//                 Appointment appointment2 = new Appointment("rambha", 3, LocalDate.of(2023, 1, 12), "mandatory yearly checkup",
//                 "9976543246");
//                 appointment2.setDoctor(doctor);

//         appointmentRepository.save(appointment2);
        

        
        


     



        


        
//     }
    
// }
