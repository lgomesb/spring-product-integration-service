package com.barbosa.ms.productintegrationservice.product-integration-service.domain.dto;

import com.barbosa.ms.productintegrationservice.product-integration-service.domain.records.ProductIntegrationServiceRecord;
import lombok.Builder;

import java.util.UUID;

public class ProductIntegrationServiceResponseDTO extends ResponseDTO {

    public static ProductIntegrationServiceResponseDTO create(ProductIntegrationServiceRecord ProductIntegrationServiceRecord) {
       return ProductIntegrationServiceResponseDTO.builder()
                .id(ProductIntegrationServiceRecord.id())
                .name(ProductIntegrationServiceRecord.name())
                .build();
    }

    @Builder
    public ProductIntegrationServiceResponseDTO(UUID id, String name) {
        super();
        super.setId(id);
        super.setName(name);
    }
}
