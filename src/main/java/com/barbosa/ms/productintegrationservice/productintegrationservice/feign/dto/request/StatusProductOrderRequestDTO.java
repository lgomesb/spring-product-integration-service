package com.barbosa.ms.productintegrationservice.productintegrationservice.feign.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StatusProductOrderRequestDTO {
    private StatusProductOrderEnum status;
}
