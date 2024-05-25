package com.barbosa.ms.productintegrationservice.productintegrationservice.feign.productorder.types;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductOrderRequestDTO {

    private String description;
    private List<OrderItemDTO> items;
}
