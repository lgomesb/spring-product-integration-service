package com.barbosa.ms.productintegrationservice.productintegrationservice.domain.dto.request;


import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.dto.DataDTO;
import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.dto.ProductOrderItemDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = false)
public class CreateProductOrderRequestDTO extends DataDTO {

    private List<ProductOrderItemDTO> items;
}
