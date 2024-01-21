package com.barbosa.ms.productintegrationservice.productintegrationservice.services.failed;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.entities.ProductIntegrationService;
import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.records.ProductIntegrationServiceRecord;
import com.barbosa.ms.productintegrationservice.productintegrationservice.repositories.ProductIntegrationServiceRepository;
import com.barbosa.ms.productintegrationservice.productintegrationservice.services.impl.ProductIntegrationServiceServiceImpl;


class ProductIntegrationServiceServiceFailedTest {

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
    void shouldFailWhenCreate() {
        given.productintegrationserviceInicietedForFailueReturn();
        given.productintegrationserviceRecordInicietedForFailueReturn();
        when.saveProductIntegrationServiceEntity();
        then.shouldBeFailueWhenCreateProductIntegrationService(DataIntegrityViolationException.class);
    }

    @Test
    void shouldFailWhenFindById() {
        given.productintegrationserviceInicietedForFailueReturn();
        when.findProductIntegrationServiceByIdWithFail();        
        then.shouldBeFailueWhenFindProductIntegrationServiceById(ObjectNotFoundException.class);
    }

    @Test
    void shouldFailWhenUpdateWithIdNonExistent() {
        given.productintegrationserviceInicietedForFailueReturn();
        given.productintegrationserviceRecordInicietedForFailueReturn();
        when.findProductIntegrationServiceByIdWithFail();        
        then.shouldBeFailueWhenFindProductIntegrationServiceById(ObjectNotFoundException.class);    
    }

    @Test
    void shouldFailWhenUpdateWithInvalidArgument() {
        given.productintegrationserviceInicietedForFailueReturn();
        given.productintegrationserviceRecordInicietedForFailueReturn();
        when.findProductIntegrationServiceById();
        when.saveProductIntegrationServiceEntity();
        then.shouldBeFailueWhenUpdateProductIntegrationService(DataIntegrityViolationException.class);        
    }

    @Test
    void shouldFailWhenDelete() {
        given.productintegrationserviceInicietedForFailueReturn();
        when.findProductIntegrationServiceByIdWithFail();
        then.shouldBeFailueWhenDeleteProductIntegrationService(ObjectNotFoundException.class);     
    }

    class Given {

        public UUID creationIdOfProductIntegrationService() {
            return UUID.randomUUID();
        }

        void productintegrationserviceInicietedForFailueReturn() {
           productintegrationservice = ProductIntegrationService.builder()
                        .id(creationIdOfProductIntegrationService())
                        .name(null)
                        .build();
        }

        void productintegrationserviceRecordInicietedForFailueReturn () {
            productintegrationserviceRecord = new ProductIntegrationServiceRecord(productintegrationservice.getId(), null);
        }
    }

    class When {
        
        public ProductIntegrationServiceRecord callCreateInProductIntegrationServiceSerivce() {
            return service.create(productintegrationserviceRecord);
        }
        
        public ProductIntegrationServiceRecord callProductIntegrationServiceServiceFindById() {
            return service.findById(given.creationIdOfProductIntegrationService());
        }

        void callProductIntegrationServiceSerivceUpdate() {
            service.update(productintegrationserviceRecord);
        }

        void callDelteInProductIntegrationServiceSerivce() {
            service.delete(given.creationIdOfProductIntegrationService());
        }

        void saveProductIntegrationServiceEntity() {            
            doThrow(new DataIntegrityViolationException("Error inserting productintegrationservice"))
                .when(repository)
                .save(any(ProductIntegrationService.class));
        }

        void findProductIntegrationServiceById() {
            when(repository.findById(any(UUID.class))).thenReturn(Optional.of(productintegrationservice));
        }

        void deleteProductIntegrationServiceEntity() {
            doNothing().when(repository).delete(any(ProductIntegrationService.class));
        }

        void findProductIntegrationServiceByIdWithFail() {
            doThrow(new ObjectNotFoundException("ProductIntegrationService", given.creationIdOfProductIntegrationService()))
                .when(repository).findById(any(UUID.class));
                
        }

    }
    
    class Then {

        public <T extends Throwable> void shouldBeFailueWhenCreateProductIntegrationService(Class<T> clazz) {
           assertThrows(clazz, () -> {
                when.callCreateInProductIntegrationServiceSerivce();
           });
        }

        
        public <T extends Throwable> void shouldBeFailueWhenFindProductIntegrationServiceById(Class<T> clazz) {
            assertThrows(clazz, () -> {
                when.callProductIntegrationServiceServiceFindById();
            });
        }
        
        public <T extends Throwable> void shouldBeFailueWhenUpdateProductIntegrationService(Class<T> clazz) {
            assertThrows(clazz, () -> {
                when.callProductIntegrationServiceSerivceUpdate();
            });
        }

        public <T extends Throwable> void shouldBeFailueWhenDeleteProductIntegrationService(Class<T> clazz) {
            assertThrows(clazz, () -> {
                when.callDelteInProductIntegrationServiceSerivce();
            });
        }
    }
}
