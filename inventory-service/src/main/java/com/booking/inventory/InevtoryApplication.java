package com.booking.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@SpringBootApplication
public class InevtoryApplication {
    public static void main(String[] args) {
        log.info("inventory-service started");
        SpringApplication.run(InevtoryApplication.class, args);
    }
}
