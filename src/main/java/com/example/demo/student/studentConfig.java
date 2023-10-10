package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class studentConfig {
    @Bean
    CommandLineRunner commandLineRunner(studentRepository repository){
        
        return args -> {

            student avijit = new student(
                    "avijit",
                    "avijitpaul.mbstu@gmail.com",
                    LocalDate.of(2000, Month.JULY, 22)

            );

            student arnab = new student(
                    "arnab",
                    "arnabpaul.mbstu@gmail.com",
                    LocalDate.of(2006, Month.JULY, 22)
            );

            repository.saveAll(
                    List.of(avijit,arnab)
            );

        };
    }
    
}
