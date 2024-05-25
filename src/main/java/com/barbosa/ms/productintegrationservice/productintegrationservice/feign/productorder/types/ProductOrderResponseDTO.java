package com.barbosa.ms.productintegrationservice.productintegrationservice.feign.productorder.types;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@Data
public class ProductOrderResponseDTO {

    private UUID id;

    private List<OrderItemDTO> items;
}
