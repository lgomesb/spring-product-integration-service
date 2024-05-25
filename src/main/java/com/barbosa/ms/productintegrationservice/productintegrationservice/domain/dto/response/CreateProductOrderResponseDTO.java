package com.barbosa.ms.productintegrationservice.productintegrationservice.domain.dto.response;

import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.dto.ProductOrderItemDTO;
import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.dto.ResponseDTO;
import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.records.ProductOrderResponseRecord;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
public class CreateProductOrderResponseDTO extends ResponseDTO {

    private List<ProductOrderItemDTO> items;

    public static CreateProductOrderResponseDTO create(ProductOrderResponseRecord productOrderRecord) {
       return CreateProductOrderResponseDTO.builder()
                .id(productOrderRecord.id())
                .description(productOrderRecord.description())
                .items(productOrderRecord.items()
                        .stream()
                        .map(ProductOrderItemDTO::create)
                        .toList())
                .build();
    }

    @Builder
    public CreateProductOrderResponseDTO(UUID id, String description, List<ProductOrderItemDTO> items) {
        super();
        this.items = items;
        super.setId(id);
        super.setDescription(description);
    }
}
