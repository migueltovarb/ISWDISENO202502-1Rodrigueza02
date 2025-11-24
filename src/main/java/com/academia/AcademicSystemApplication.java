package com.academia;

import com.academia.model.User;
import com.academia.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AcademicSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcademicSystemApplication.class, args);
    }

}
