package com.barbosa.ms.productintegrationservice.productintegrationservice.domain.records;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ProductOrderItemRecord(UUID productId, int quantity) {
}
