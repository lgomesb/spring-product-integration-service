package com.barbosa.ms.productintegrationservice.productintegrationservice.services.succeed;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.entities.ProductIntegrationService;
import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.records.ProductIntegrationServiceRecord;
import com.barbosa.ms.productintegrationservice.productintegrationservice.repositories.ProductIntegrationServiceRepository;
import com.barbosa.ms.productintegrationservice.productintegrationservice.services.impl.ProductIntegrationServiceServiceImpl;


class ProductIntegrationServiceServiceSucceedTest {

    @InjectMocks
    private ProductIntegrationServiceServiceImpl service;

    @Mock
    private ProductIntegrationServiceRepository repository;
    
    private ProductIntegrationService productintegrationservice;
    private ProductIntegrationServiceRecord productintegrationserviceRecord;
    private Given given = new Given();
    private When when = new When();
    private Then then = new Then();


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSuccessWhenCreate() {
        given.productintegrationserviceInicietedForSuccessfulReturn();
        given.productintegrationserviceRecordInicietedForSuccessfulReturn();
        when.saveProductIntegrationServiceEntity();
        ProductIntegrationServiceRecord record = when.callCreateInProductIntegrationServiceSerivce();
        then.shouldBeSuccessfulValidationRules(record);
    }

    @Test
    void shouldSuccessWhenFindById() {
        given.productintegrationserviceInicietedForSuccessfulReturn();
        when.findProductIntegrationServiceById();
        ProductIntegrationServiceRecord record = when.callProductIntegrationServiceServiceFindById();
        then.shouldBeSuccessfulValidationRules(record);
    }

    @Test
    void shouldSuccessWhenUpdate() {
        given.productintegrationserviceInicietedForSuccessfulReturn();
        given.productintegrationserviceRecordInicietedForSuccessfulReturn();
        when.findProductIntegrationServiceById();
        when.callProductIntegrationServiceServiceFindById();
        when.saveProductIntegrationServiceEntity();
        when.callProductIntegrationServiceSerivceUpdate();
        then.shouldBeSuccessfulArgumentValidationByUpdate();        
    }

    @Test
    void shouldSuccessWhenDelete() {
        given.productintegrationserviceInicietedForSuccessfulReturn();
        when.findProductIntegrationServiceById();
        when.deleteProductIntegrationServiceEntity();
        when.callDelteInProductIntegrationServiceSerivce();    
        then.shouldBeSuccessfulArgumentValidationByDelete();    
    }

    @Test
    void shouldSuccessWhenListAll() {
        given.productintegrationserviceInicietedForSuccessfulReturn();
        when.findAllProductIntegrationService();
        List<ProductIntegrationServiceRecord>  productintegrationserviceRecords = when.callListAllInProductIntegrationServiceService();
        then.shouldBeSuccessfulArgumentValidationByListAll(productintegrationserviceRecords);
    }

    class Given {

        public UUID creationIdOfProductIntegrationService() {
            return UUID.randomUUID();
        }

        void productintegrationserviceInicietedForSuccessfulReturn() {
           productintegrationservice = ProductIntegrationService.builder()
                        .id(creationIdOfProductIntegrationService())
                        .name("ProductIntegrationService-Test-Success")
                        .build();
        }

        void productintegrationserviceRecordInicietedForSuccessfulReturn () {
            productintegrationserviceRecord = new ProductIntegrationServiceRecord(productintegrationservice.getId(), productintegrationservice.getName());
        }
    }

    class When {

        void saveProductIntegrationServiceEntity() {
            when(repository.save(any(ProductIntegrationService.class)))
            .thenReturn(productintegrationservice);
        }

        void callProductIntegrationServiceSerivceUpdate() {
            service.update(productintegrationserviceRecord);
        }

        void callDelteInProductIntegrationServiceSerivce() {
            service.delete(given.creationIdOfProductIntegrationService());
        }

        void deleteProductIntegrationServiceEntity() {
            doNothing().when(repository).delete(any(ProductIntegrationService.class));
        }

        public ProductIntegrationServiceRecord callProductIntegrationServiceServiceFindById() {
            return service.findById(given.creationIdOfProductIntegrationService());
        }

        void findProductIntegrationServiceById() {
            when(repository.findById(any(UUID.class))).thenReturn(Optional.of(productintegrationservice));
        }

        public ProductIntegrationServiceRecord callCreateInProductIntegrationServiceSerivce() {
            return service.create(productintegrationserviceRecord);
        }

        void findAllProductIntegrationService() {
            when(repository.findAll()).thenReturn(Collections.singletonList(productintegrationservice));
        }

        public List<ProductIntegrationServiceRecord> callListAllInProductIntegrationServiceService() {
            return service.listAll();
        }
    }
    
    class Then {

        void shouldBeSuccessfulValidationRules(ProductIntegrationServiceRecord record) {
            assertNotNull(record);
            assertNotNull(record.name());
            assertEquals(record.name(), productintegrationservice.getName());
            assertNotNull(record.id());
            assertEquals(record.id(), productintegrationservice.getId());
        }

        void shouldBeSuccessfulArgumentValidationByDelete() {
            ArgumentCaptor<ProductIntegrationService> productintegrationserviceCaptor = ArgumentCaptor.forClass(ProductIntegrationService.class);
            verify(repository).delete(productintegrationserviceCaptor.capture());
            assertNotNull(productintegrationserviceCaptor.getValue());
            assertEquals(productintegrationserviceCaptor.getValue().getName(),productintegrationservice.getName());
        }

        void shouldBeSuccessfulArgumentValidationByUpdate() {
            ArgumentCaptor<ProductIntegrationService> productintegrationserviceCaptor = ArgumentCaptor.forClass(ProductIntegrationService.class);
            verify(repository).save(productintegrationserviceCaptor.capture());
            assertNotNull(productintegrationserviceCaptor.getValue());
            assertEquals(productintegrationserviceCaptor.getValue().getName(),productintegrationservice.getName());
        }

        void shouldBeSuccessfulArgumentValidationByListAll(List<ProductIntegrationServiceRecord> productintegrationserviceRecords) {
            assertNotNull(productintegrationserviceRecords);
            assertFalse(productintegrationserviceRecords.isEmpty());
        }
    }
}
