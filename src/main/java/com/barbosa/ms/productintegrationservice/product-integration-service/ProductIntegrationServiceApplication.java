package com.barbosa.ms.productintegrationservice.product-integration-service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductIntegrationServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ProductIntegrationServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("ProductIntegrationService");
    }
}