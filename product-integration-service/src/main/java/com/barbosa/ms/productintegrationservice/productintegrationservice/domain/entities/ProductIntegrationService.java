package com.barbosa.ms.productintegrationservice.productintegrationservice.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Table(name = "productintegrationservice")
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
