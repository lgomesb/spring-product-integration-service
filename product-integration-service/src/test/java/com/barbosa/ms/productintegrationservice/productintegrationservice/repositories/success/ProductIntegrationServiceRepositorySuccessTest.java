package com.barbosa.ms.productintegrationservice.productintegrationservice.repositories.success;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.Optional;
import java.util.stream.Stream;

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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import com.barbosa.ms.productintegrationservice.productintegrationservice.ProductIntegrationServiceApplicationTests;
import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.entities.ProductIntegrationService;
import com.barbosa.ms.productintegrationservice.productintegrationservice.repositories.ProductIntegrationServiceRepository;

@ActiveProfiles(value = "test")
@DataJpaTest()
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(classes = ProductIntegrationServiceApplicationTests.class)
@TestInstance(Lifecycle.PER_CLASS)
class ProductIntegrationServiceRepositorySuccessTest {

    @Autowired
    private ProductIntegrationServiceRepository repository;

    private static Stream<Arguments> provideProductIntegrationServiceData() {        
        return Stream.of(
          Arguments.of("ProductIntegrationService-Test-01"),
          Arguments.of("ProductIntegrationService-Test-02")
        );
    }

    
    @Test 
    @Order(0)
    void shouldSuccessfulInjectComponent() {
        assertNotNull(repository);
    }

    @Order(1)
    @ParameterizedTest
    @MethodSource("provideProductIntegrationServiceData")
    void shouldWhenCallCreate(String productintegrationserviceName) {
        ProductIntegrationService productintegrationservice = repository.saveAndFlush(new ProductIntegrationService(productintegrationserviceName));
        assertNotNull(productintegrationservice, "Should return ProductIntegrationService is not null");
        assertNotNull(productintegrationservice.getId());
        assertEquals(productintegrationserviceName, productintegrationservice.getName());        
    }


    @Order(2)
    @ParameterizedTest
    @MethodSource("provideProductIntegrationServiceData")
    void shouldWhenCallFindById(String productintegrationserviceName) {
        ProductIntegrationService productintegrationservice = repository.save(new ProductIntegrationService(productintegrationserviceName));
        Optional<ProductIntegrationService> oProductIntegrationService = repository.findById(productintegrationservice.getId());
        assertNotNull(oProductIntegrationService.get(), "Should return ProductIntegrationService is not null");
        assertNotNull(oProductIntegrationService.get().getId(), "Should return ProductIntegrationService ID is not null");
        assertNotNull(oProductIntegrationService.get().getName(), "Should return ProductIntegrationService NAME is not null");
    }

  
    @Order(3)
    @ParameterizedTest
    @MethodSource("provideProductIntegrationServiceData")
    void shouldWhenCallUpdate(String productintegrationserviceName) {
        String productintegrationserviceNameUpdate = "Test-Update-ProductIntegrationService";
        ProductIntegrationService productintegrationservice = repository.save(new ProductIntegrationService(productintegrationserviceName));
        Optional<ProductIntegrationService> oProductIntegrationService = repository.findById(productintegrationservice.getId());
        ProductIntegrationService newProductIntegrationService = oProductIntegrationService.get();
        newProductIntegrationService.setName(productintegrationserviceNameUpdate);
        newProductIntegrationService = repository.save(newProductIntegrationService);
        assertEquals(productintegrationserviceNameUpdate, newProductIntegrationService.getName());
    }
  
    @Order(4)
    @ParameterizedTest
    @MethodSource("provideProductIntegrationServiceData")
    void shouldWhenCallDelete(String productintegrationserviceName) {
        ProductIntegrationService productintegrationservice = repository.save(new ProductIntegrationService(productintegrationserviceName));
        Optional<ProductIntegrationService> oProductIntegrationService = repository.findById(productintegrationservice.getId());
        repository.delete(oProductIntegrationService.get());
        Optional<ProductIntegrationService> findProductIntegrationService = repository.findById(oProductIntegrationService.get().getId());
        assertFalse(findProductIntegrationService.isPresent());
    }
}
