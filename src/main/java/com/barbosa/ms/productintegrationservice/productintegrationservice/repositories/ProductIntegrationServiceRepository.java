package com.barbosa.ms.productintegrationservice.productintegrationservice.repositories;

import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.entities.ProductIntegrationService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductIntegrationServiceRepository extends JpaRepository<ProductIntegrationService, UUID> {
    
}
