package com.man.studentcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StudentCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentCenterApplication.class, args);
    }

}
