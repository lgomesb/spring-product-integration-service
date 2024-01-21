package com.barbosa.ms.productintegrationservice.product-integration-service.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Table(name = "product-integration-service")
@Entity
public class ProductIntegrationService extends AbstractEntity {
    
    public ProductIntegrationService(String name) {
        super();
        super.setName(name);
    }
    
    @Builder()
    public ProductIntegrationService(UUID id, String name) {
        super();
        super.setId(id);
        super.setName(name);
    }
}
