package com.demo;

import com.demo.entities.patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.demo.repo.PatientRepo;

import java.util.Date;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    PatientRepo patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
       /* patientRepository.save(new patient(null,"Mohamed",new Date(),230,false));
        patientRepository.save(new patient(null,"Mustapha",new Date(),145,true));
        patientRepository.save(new patient(null,"Khalid",new Date(),224,false));
        patientRepository.save(new patient(null,"Yasser",new Date(),316,false));
        patientRepository.save(new patient(null,"Zakaria",new Date(),102,true));*/
        //patientRepository.findAll().forEach(patient -> System.out.println(patient));
    }
}

