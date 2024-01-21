package com.barbosa.ms.productintegrationservice.productintegrationservice.domain.dto;

import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.records.ProductIntegrationServiceRecord;
import lombok.Builder;

import java.util.UUID;

public class ProductIntegrationServiceResponseDTO extends ResponseDTO {

    public static ProductIntegrationServiceResponseDTO create(ProductIntegrationServiceRecord productintegrationserviceRecord) {
       return ProductIntegrationServiceResponseDTO.builder()
                .id(productintegrationserviceRecord.id())
                .name(productintegrationserviceRecord.name())
                .build();
    }

    @Builder
    public ProductIntegrationServiceResponseDTO(UUID id, String name) {
        super();
        super.setId(id);
        super.setName(name);
    }
}
