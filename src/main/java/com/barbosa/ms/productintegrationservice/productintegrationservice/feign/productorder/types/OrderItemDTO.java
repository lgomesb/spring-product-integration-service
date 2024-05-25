package com.barbosa.ms.productintegrationservice.productintegrationservice.feign.productorder.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class OrderItemDTO {

    private UUID productId;
    private int quantity;

}
