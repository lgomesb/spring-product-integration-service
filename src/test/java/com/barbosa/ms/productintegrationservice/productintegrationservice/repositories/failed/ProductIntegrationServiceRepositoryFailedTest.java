package com.barbosa.ms.productintegrationservice.productintegrationservice.repositories.failed;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import com.barbosa.ms.productintegrationservice.productintegrationservice.ProductIntegrationServiceApplicationTests;
import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.entities.ProductIntegrationService;
import com.barbosa.ms.productintegrationservice.productintegrationservice.repositories.ProductIntegrationServiceRepository;

import jakarta.validation.ConstraintViolationException;

@ActiveProfiles(value = "test")
@DataJpaTest()
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(classes = ProductIntegrationServiceApplicationTests.class)
@TestInstance(Lifecycle.PER_CLASS)
class ProductIntegrationServiceRepositoryFailedTest {

    @Autowired
    private ProductIntegrationServiceRepository repository;

    private static Stream<Arguments> provideProductIntegrationServiceData() {        
        return Stream.of(
          Arguments.of("ProductIntegrationService-Test-01"),
          Arguments.of("ProductIntegrationService-Test-02")
        );
    }

    
    @BeforeAll
    void shouldSuccessfulInjectComponent() {
        assertNotNull(repository);
    }

    @Order(0)
    @Test()
    @DisplayName("Should return Exception when ProductIntegrationService not null")
    void shouldFailWhenCallCreate() {
        assertThrows(ConstraintViolationException.class, () -> {
            repository.saveAndFlush(new ProductIntegrationService(null));
        }, "Should return Error when ProductIntegrationService not null");
    }


    @Order(1)
    @ParameterizedTest
    @MethodSource("provideProductIntegrationServiceData")
    void shouldFailWhenCallFindById(String productintegrationserviceName) {
        repository.save(new ProductIntegrationService(productintegrationserviceName));
        Optional<ProductIntegrationService> oProductIntegrationService = repository.findById(UUID.randomUUID());
        assertThrows( ObjectNotFoundException.class, () -> {
            oProductIntegrationService.orElseThrow(() ->
                 new ObjectNotFoundException("ProductIntegrationService", UUID.randomUUID()));
        });
    }

  
    @Order(2)
    @ParameterizedTest
    @MethodSource("provideProductIntegrationServiceData")
    void shouldFailWhenCallUpdate(String productintegrationserviceName) {
        String productintegrationserviceNameUpdate = "";
        ProductIntegrationService productintegrationservice = repository.save(new ProductIntegrationService(productintegrationserviceName));
        Optional<ProductIntegrationService> oProductIntegrationService = repository.findById(productintegrationservice.getId());        
        assertThrows(ConstraintViolationException.class, () -> {
            ProductIntegrationService newProductIntegrationService = oProductIntegrationService.get();
            newProductIntegrationService.setName(productintegrationserviceNameUpdate);
            repository.saveAndFlush(newProductIntegrationService);
        }, "Should return Error when ProductIntegrationService not blank or empty");
    }
  
    @Order(3)
    @ParameterizedTest
    @MethodSource("provideProductIntegrationServiceData")
    void shouldFailWhenCallDelete(String productintegrationserviceName) {
        ProductIntegrationService productintegrationservice = new ProductIntegrationService(UUID.randomUUID(), productintegrationserviceName);
        Optional<ProductIntegrationService> oProductIntegrationService = repository.findById(productintegrationservice.getId());
        assertThrows( InvalidDataAccessApiUsageException.class, () -> {
            repository.delete(oProductIntegrationService.orElse(null));
        }, "Should return Error when ProductIntegrationService not blank or empty");
    }
}
