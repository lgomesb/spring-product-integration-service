package com.barbosa.ms.productintegrationservice.product-integration-service.services.impl;

import com.barbosa.ms.productintegrationservice.product-integration-service.domain.entities.ProductIntegrationService;
import com.barbosa.ms.productintegrationservice.product-integration-service.domain.records.ProductIntegrationServiceRecord;
import com.barbosa.ms.productintegrationservice.product-integration-service.repositories.ProductIntegrationServiceRepository;
import com.barbosa.ms.productintegrationservice.product-integration-service.services.ProductIntegrationServiceService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ProductIntegrationServiceServiceImpl implements ProductIntegrationServiceService {

    @Autowired
    private ProductIntegrationServiceRepository repository;

    @Override
    public ProductIntegrationServiceRecord create(ProductIntegrationServiceRecord recordObject) {
        ProductIntegrationService ProductIntegrationService = repository.save(new ProductIntegrationService(recordObject.name()) );
        return new ProductIntegrationServiceRecord(ProductIntegrationService.getId(), ProductIntegrationService.getName());
    }

    @Override
    public ProductIntegrationServiceRecord findById(UUID id) {
        ProductIntegrationService ProductIntegrationService = this.getProductIntegrationServiceById(id);
        return new ProductIntegrationServiceRecord(ProductIntegrationService.getId(), ProductIntegrationService.getName());
    }

    
    @Override
    public void update(ProductIntegrationServiceRecord recordObject) {
        ProductIntegrationService ProductIntegrationService = this.getProductIntegrationServiceById(recordObject.id());
        ProductIntegrationService.setName(recordObject.name());
        ProductIntegrationService.setModifieldOn(LocalDateTime.now());
        ProductIntegrationService.setModifiedBy("99999");
        repository.save(ProductIntegrationService);      
    }
    
    @Override
    public void delete(UUID id) {
        ProductIntegrationService ProductIntegrationService = this.getProductIntegrationServiceById(id);
        repository.delete(ProductIntegrationService);

    }
    
    private ProductIntegrationService getProductIntegrationServiceById(UUID id) {
        return repository.findById(id)
                  .orElseThrow(()-> new ObjectNotFoundException("ProductIntegrationService", id));
    }

    @Override
    public List<ProductIntegrationServiceRecord> listAll() {
        return repository.findAll()
            .stream()
            .map(entity -> ProductIntegrationServiceRecord.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .build())
            .toList();
    }
    
    
}
