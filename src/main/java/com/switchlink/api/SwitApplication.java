package com.switchlink.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SwitApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwitApplication.class, args);

    }
}
