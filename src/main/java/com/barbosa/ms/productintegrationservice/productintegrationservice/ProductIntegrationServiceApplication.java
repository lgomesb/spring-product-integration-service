package com.barbosa.ms.productintegrationservice.productintegrationservice;

<<<<<<< HEAD
import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.records.ProductOrderItemRecord;
import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.records.ProductOrderRequestRecord;
import com.barbosa.ms.productintegrationservice.productintegrationservice.services.ProductIntegrationServiceService;
=======
import com.barbosa.ms.productintegrationservice.productintegrationservice.feign.ProductCatalogMgmtClient;
import com.barbosa.ms.productintegrationservice.productintegrationservice.feign.dto.request.ProductRequestDTO;
import com.barbosa.ms.productintegrationservice.productintegrationservice.feign.dto.response.ProductResponseDTO;
>>>>>>> 8a1edaeb98666a704c4bd965d293aa64b204bc54
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
<<<<<<< HEAD

        System.out.println("PRODUCT ORDER");
        ProductOrderRequestRecord productOrderRequestRecord = ProductOrderRequestRecord.builder()
                .description("test-" + System.currentTimeMillis())
                .items(Collections.singletonList(new ProductOrderItemRecord(UUID.fromString("65b2d800-1e64-410a-a196-935f64ecec3c"), 4)))
                .build();
        System.out.println(service.createProductOrder(productOrderRequestRecord));

=======
//        ProductResponseDTO response = productFeign.getProduct(UUID.fromString("2179d1ef-5c0b-4855-af9b-686192ccc99b"));
        ProductRequestDTO request = ProductRequestDTO.builder()
                .name("teste-01")
                .idCategory("26dd8bcc-aaba-486d-827a-8ce5933432c0")
                .build();
        ProductResponseDTO response = productFeign.create(request);
        System.out.println("::::::::::::::::::: RESPONSE PRODUCT FEIGN CLIENT :::::::::::::::::::");
        System.out.println(response);
>>>>>>> 8a1edaeb98666a704c4bd965d293aa64b204bc54
    }
}