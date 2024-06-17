package com.barbosa.ms.productintegrationservice.productintegrationservice.feign.productorder.types;

import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.enums.StatusProductOrderEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StatusProductOrderRequestDTO {
    private StatusProductOrderEnum status;
}
