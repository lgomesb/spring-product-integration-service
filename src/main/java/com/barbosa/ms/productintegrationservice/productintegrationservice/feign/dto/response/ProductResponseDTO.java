package com.barbosa.ms.productintegrationservice.productintegrationservice.feign.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductResponseDTO {

    private UUID id;
    private String idCategory;
    private String name;

}
