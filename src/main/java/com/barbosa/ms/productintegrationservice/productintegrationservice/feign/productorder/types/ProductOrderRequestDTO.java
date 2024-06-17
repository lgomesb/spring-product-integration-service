package com.barbosa.ms.productintegrationservice.productintegrationservice.feign.productorder.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductOrderRequestDTO {

    private String description;
    private List<OrderItemDTO> items;
}
