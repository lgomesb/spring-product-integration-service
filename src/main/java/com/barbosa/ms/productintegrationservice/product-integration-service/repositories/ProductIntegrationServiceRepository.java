package com.barbosa.ms.productintegrationservice.product-integration-service.repositories;

import com.barbosa.ms.productintegrationservice.product-integration-service.domain.entities.ProductIntegrationService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductIntegrationServiceRepository extends JpaRepository<ProductIntegrationService, UUID> {
    
}
