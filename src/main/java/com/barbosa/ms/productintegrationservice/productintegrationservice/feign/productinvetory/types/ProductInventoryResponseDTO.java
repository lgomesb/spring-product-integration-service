package com.barbosa.ms.productintegrationservice.productintegrationservice.feign.productinvetory.types;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductInventoryResponseDTO {

    private UUID id;
    private UUID productId;
    private Integer quantity;

}
