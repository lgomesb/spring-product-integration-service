package com.barbosa.ms.productintegrationservice.productintegrationservice.feign.productcatalog.types;

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
