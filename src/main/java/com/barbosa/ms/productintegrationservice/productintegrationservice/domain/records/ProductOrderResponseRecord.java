package com.barbosa.ms.productintegrationservice.productintegrationservice.domain.records;

import lombok.Builder;

import java.util.List;
import java.util.UUID;


@Builder
public record ProductOrderResponseRecord(UUID id, String description, List<ProductOrderItemRecord> items) {
    
}
