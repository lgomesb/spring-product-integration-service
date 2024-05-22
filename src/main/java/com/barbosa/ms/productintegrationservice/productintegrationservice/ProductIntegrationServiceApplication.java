package com.barbosa.ms.productintegrationservice.productintegrationservice;

import com.barbosa.ms.productintegrationservice.productintegrationservice.feign.ProductCatalogMgmtClient;
import com.barbosa.ms.productintegrationservice.productintegrationservice.feign.dto.response.ProductResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class ProductIntegrationServiceApplication implements CommandLineRunner {

    @Autowired
    private ProductCatalogMgmtClient productFeign;

    public static void main(String[] args) {
        SpringApplication.run(ProductIntegrationServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ProductResponseDTO response = productFeign.getProduct(UUID.fromString("2179d1ef-5c0b-4855-af9b-686192ccc99b"));
        System.out.println("::::::::::::::::::: RESPONSE PRODUCT FEIGN CLIENT :::::::::::::::::::");
        System.out.println(response);
    }
}