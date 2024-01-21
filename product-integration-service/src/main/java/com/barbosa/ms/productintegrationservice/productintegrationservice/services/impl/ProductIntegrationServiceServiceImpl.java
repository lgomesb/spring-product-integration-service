package com.barbosa.ms.productintegrationservice.productintegrationservice.services.impl;

import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.entities.ProductIntegrationService;
import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.records.ProductIntegrationServiceRecord;
import com.barbosa.ms.productintegrationservice.productintegrationservice.repositories.ProductIntegrationServiceRepository;
import com.barbosa.ms.productintegrationservice.productintegrationservice.services.ProductIntegrationServiceService;
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
        ProductIntegrationService productintegrationservice = repository.save(new ProductIntegrationService(recordObject.name()) );
        return new ProductIntegrationServiceRecord(productintegrationservice.getId(), productintegrationservice.getName());
    }

    @Override
    public ProductIntegrationServiceRecord findById(UUID id) {
        ProductIntegrationService productintegrationservice = this.getProductIntegrationServiceById(id);
        return new ProductIntegrationServiceRecord(productintegrationservice.getId(), productintegrationservice.getName());
    }

    
    @Override
    public void update(ProductIntegrationServiceRecord recordObject) {
        ProductIntegrationService productintegrationservice = this.getProductIntegrationServiceById(recordObject.id());
        productintegrationservice.setName(recordObject.name());
        productintegrationservice.setModifieldOn(LocalDateTime.now());
        productintegrationservice.setModifiedBy("99999");
        repository.save(productintegrationservice);
    }
    
    @Override
    public void delete(UUID id) {
        ProductIntegrationService productintegrationservice = this.getProductIntegrationServiceById(id);
        repository.delete(productintegrationservice);

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
