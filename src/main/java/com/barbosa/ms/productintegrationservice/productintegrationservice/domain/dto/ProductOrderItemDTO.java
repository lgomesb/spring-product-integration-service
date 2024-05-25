package com.barbosa.ms.productintegrationservice.productintegrationservice.domain.dto;

import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.records.ProductOrderItemRecord;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ProductOrderItemDTO {

    private UUID productID;
    private int quantity;

    public static ProductOrderItemDTO create(ProductOrderItemRecord productOrderItemRecord) {
        return new ProductOrderItemDTO(productOrderItemRecord.productId(), productOrderItemRecord.quantity());
    }
}
