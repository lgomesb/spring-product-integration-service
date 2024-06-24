package com.barbosa.ms.productintegrationservice.productintegrationservice.feign.productinvetory.types;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductInventoryRequestDTO {

    private UUID productId;

    private UUID productOrderId;

    private Integer quantity;
}
