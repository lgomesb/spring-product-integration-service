package com.barbosa.ms.productintegrationservice.productintegrationservice.controllers;

import com.barbosa.ms.productintegrationservice.productintegrationservice.ProductIntegrationServiceApplicationTests;
import com.barbosa.ms.productintegrationservice.productintegrationservice.controller.ProductIntegrationServiceController;
import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.records.ProductOrderResponseRecord;
import com.barbosa.ms.productintegrationservice.productintegrationservice.services.ProductIntegrationServiceService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.net.UnknownHostException;
import java.util.UUID;


@ActiveProfiles(value = "test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {ProductIntegrationServiceApplicationTests.class}, webEnvironment = WebEnvironment.DEFINED_PORT)
@TestInstance(Lifecycle.PER_CLASS)
class ProductIntegrationServiceControllerTest {

    private static UUID STATIC_UUID;
    private static final String STATIC_URI = "/product-integration-service/";

    @LocalServerPort
    private int port;

    @MockBean
    private ProductIntegrationServiceService service;

    @InjectMocks
    private ProductIntegrationServiceController controller;

    private ProductOrderResponseRecord productintegrationserviceRecord;

    @BeforeEach
    void setup() {
        productintegrationserviceRecord = ProductOrderResponseRecord.builder()
                .id(UUID.randomUUID())
                .description("Test-ProductIntegrationService-01")
                .build();

    }

    @Test
    @Order(0)
    void shouldSucceededWhenCallCreate() throws UnknownHostException {

    }


   
}
