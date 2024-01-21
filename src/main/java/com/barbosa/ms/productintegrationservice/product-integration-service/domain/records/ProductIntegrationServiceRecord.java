package com.barbosa.ms.productintegrationservice.product-integration-service.domain.records;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ProductIntegrationServiceRecord(UUID id, String name) {
    
}
