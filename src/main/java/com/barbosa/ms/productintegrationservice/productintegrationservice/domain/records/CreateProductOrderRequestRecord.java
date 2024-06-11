package com.barbosa.ms.productintegrationservice.productintegrationservice.domain.records;

import lombok.Builder;

import java.util.List;

@Builder
public record CreateProductOrderRequestRecord(String description, List<ProductOrderItemRecord> items) {
}
