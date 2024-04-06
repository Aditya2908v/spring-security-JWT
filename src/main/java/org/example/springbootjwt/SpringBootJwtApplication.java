package org.example.springbootjwt;

import org.example.springbootjwt.models.Role;
import org.example.springbootjwt.requestResponse.RegisterRequest;
import org.example.springbootjwt.services.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static org.example.springbootjwt.models.Role.ADMIN;

@SpringBootApplication
public class SpringBootJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJwtApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(AuthService authService) {
        return args -> {
            var admin = RegisterRequest.builder()
                    .firstName("Admin")
                    .lastName("admin")
                    .email("admin@admin.com")
                    .password("admin")
                    .role(ADMIN)
                    .build();
            System.out.println("Admin token: " + authService.register(admin).getAccessToken());
        };
    }
}
