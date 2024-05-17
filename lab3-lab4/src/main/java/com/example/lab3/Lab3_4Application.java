package com.example.lab3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Lab3_4Application {
    public static void main(String[] args) {

        SpringApplication.run(Lab3_4Application.class, args);
    }

}
