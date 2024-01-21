package com.barbosa.ms.productintegrationservice.productintegrationservice.controllers;

import com.barbosa.ms.productintegrationservice.productintegrationservice.ProductIntegrationServiceApplicationTests;
import com.barbosa.ms.productintegrationservice.productintegrationservice.controller.ProductIntegrationServiceController;
import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.records.ProductIntegrationServiceRecord;
import com.barbosa.ms.productintegrationservice.productintegrationservice.services.ProductIntegrationServiceService;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.net.UnknownHostException;
import java.util.Collections;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;


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

    private ProductIntegrationServiceRecord productintegrationserviceRecord;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        productintegrationserviceRecord = ProductIntegrationServiceRecord.builder()
                .id(UUID.randomUUID())
                .name("Test-ProductIntegrationService-01")
                .build();

    }

    @Test
    @Order(0)
    void shouldSucceededWhenCallCreate() throws UnknownHostException {

        when(service.create(any(ProductIntegrationServiceRecord.class))).thenReturn(productintegrationserviceRecord);

        Response response = given()
            .port(port)
            .contentType(ContentType.JSON)
            .body("{\"name\": \""+ productintegrationserviceRecord.name() +"\"}")
            .log().all()
            .when()
            .post(STATIC_URI)
            .then()
            .assertThat()
            .statusCode(HttpStatus.CREATED.value())
            .extract()
            .response();
            
        assertNotNull(response);
        String idProductIntegrationService = response.getHeader("Location");
        idProductIntegrationService = idProductIntegrationService.substring(idProductIntegrationService.lastIndexOf("/")+1);
        assertNotNull(idProductIntegrationService);
        assertFalse(idProductIntegrationService.isEmpty());
        STATIC_UUID = UUID.fromString(idProductIntegrationService);

    }

    @Test
    @Order(1)
    void shouldSucceededWhenCallFindById() {
        when(service.findById(any(UUID.class))).thenReturn(productintegrationserviceRecord);

        Response response = given()
            .port(port)
            .contentType(ContentType.JSON)
            .pathParam("id", STATIC_UUID.toString())
            .when()
            .get(STATIC_URI + "{id}")
            .then()
            .log().all()
            .assertThat()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .response();

        assertNotNull(response);
        assertNotNull(response.getBody().jsonPath().getString("id"));

    }

    @Test
    @Order(2)
    void shouldSucceededWhenCallUpdate() {

        given()
            .port(port)
            .contentType(ContentType.JSON)
            .pathParam("id", STATIC_UUID.toString())
            .body("{\"name\": \"Teste-2\"}")
            .log().all()
            .when()
            .put(STATIC_URI + "{id}")
            .then()
            .assertThat()
            .statusCode(HttpStatus.ACCEPTED.value());

    }

    @Test
    @Order(3)
    void shouldSucceededWhenCallDelete() {

        given()
            .port(port)
            .contentType(ContentType.JSON)
            .pathParam("id", STATIC_UUID.toString())
            .log().all()
            .when()
            .delete(STATIC_URI + "{id}")
            .then()
            .assertThat()
            .statusCode(HttpStatus.NO_CONTENT.value());

    }

    @Test
    @Order(4)
    void shouldSucceededWhenCallListAll() {
        when(service.listAll()).thenReturn(
                Collections.singletonList(new ProductIntegrationServiceRecord(STATIC_UUID, "Test-ProductIntegrationService-01")));

        given()
                .port(port)
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(STATIC_URI)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());

    }

   
}
