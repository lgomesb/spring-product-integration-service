package com.barbosa.ms.productintegrationservice.productintegrationservice;

import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.records.ProductOrderItemRecord;
import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.records.ProductOrderRequestRecord;
import com.barbosa.ms.productintegrationservice.productintegrationservice.services.ProductIntegrationServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.Collections;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class ProductIntegrationServiceApplication implements CommandLineRunner {

    @Autowired
    private ProductIntegrationServiceService service;

    public static void main(String[] args) {
        SpringApplication.run(ProductIntegrationServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("PRODUCT ORDER");
        ProductOrderRequestRecord productOrderRequestRecord = ProductOrderRequestRecord.builder()
                .description("test-" + System.currentTimeMillis())
                .items(Collections.singletonList(new ProductOrderItemRecord(UUID.fromString("65b2d800-1e64-410a-a196-935f64ecec3c"), 4)))
                .build();
        System.out.println(service.createProductOrder(productOrderRequestRecord));

    }
}