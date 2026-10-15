package com.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatalogApplication {
    public static void main(String[] args) {
        System.out.println("catalog-service started-build test");
        SpringApplication.run(CatalogApplication.class, args);
    }
}
