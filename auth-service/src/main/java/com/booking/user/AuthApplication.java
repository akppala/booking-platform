
package com.booking.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthApplication {
    public static void main(String[] args) {
        System.out.println("auth-service started");
        SpringApplication.run(AuthApplication.class, args);
    }
}
