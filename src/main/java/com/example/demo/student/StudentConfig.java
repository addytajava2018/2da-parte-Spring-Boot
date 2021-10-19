package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class StudentConfig {

    /*@Bean
    CommandLineRunner commandLineRunner (StudentRepository repository){

        return args-> {
           Student Juan= new Student(
                    "Jeferson",
                    "jeferson@gmail.com",
                    LocalDate.of(2000, JANUARY,5)

            );

            Student Pedro= new Student(
                    "Enrique",
                    "enrique@gmail.com",
                    LocalDate.of(2002, JANUARY,5)

            );
            repository.saveAll(List.of(Juan,Pedro));

        };

    }*/

}
