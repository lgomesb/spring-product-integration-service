package com.barbosa.ms.productintegrationservice.productintegrationservice.feign.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ProductRequestDTO {

    private String name;
    private String idCategory;

}
