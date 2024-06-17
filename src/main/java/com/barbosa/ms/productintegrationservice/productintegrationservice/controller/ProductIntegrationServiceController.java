package com.barbosa.ms.productintegrationservice.productintegrationservice.controller;

import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.dto.request.CreateProductOrderRequestDTO;
import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.dto.response.CreateProductOrderResponseDTO;
import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.records.ProductOrderItemRecord;
import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.records.CreateProductOrderRequestRecord;
import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.records.ProductOrderResponseRecord;
import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.enums.StatusProductOrderEnum;
import com.barbosa.ms.productintegrationservice.productintegrationservice.services.ProductIntegrationServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "ProductIntegrationService", description = "Endpoints for ProductIntegrationService operations")
@RestController
@RequestMapping("/product-order")
public class ProductIntegrationServiceController {

    private final ProductIntegrationServiceService service;

    public ProductIntegrationServiceController(ProductIntegrationServiceService service) {
        this.service = service;
    }

    @Operation(summary = "Create Product Order", description = "Create a new Product Order", tags = { "ProductIntegrationService" })
    @PostMapping
    public ResponseEntity<CreateProductOrderResponseDTO> createProductOrder(@RequestBody @Valid CreateProductOrderRequestDTO dto) {

        ProductOrderResponseRecord productOrderResponseRecord = service.createProductOrder(CreateProductOrderRequestRecord.builder()
                .description(dto.getDescription())
                .items(dto.getItems().stream()
                        .map(i -> new ProductOrderItemRecord(i.getProductID(), i.getQuantity()))
                        .toList())
                .build());

        CreateProductOrderResponseDTO response = CreateProductOrderResponseDTO.create(productOrderResponseRecord);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Approve Product Order", description = "Approve a Product Order", tags = { "ProductIntegrationService" })
    @PatchMapping("/{id}/reopen")
    public ResponseEntity<Object> reopenProductOrder(@PathVariable("id") UUID productOrderId) {
        service.updateStatusProductOrder(productOrderId, StatusProductOrderEnum.DRAFT);
        return ResponseEntity.accepted().build();
    }

    @Operation(summary = "Approve Product Order", description = "Approve a Product Order", tags = { "ProductIntegrationService" })
    @PatchMapping("/{id}/approve")
    public ResponseEntity<Object> approveProductOrder(@PathVariable("id") UUID productOrderId) {
        service.updateStatusProductOrder(productOrderId, StatusProductOrderEnum.APPROVED);
        return ResponseEntity.accepted().build();
    }

    @Operation(summary = "Reject Product Order", description = "Reject a Product Order", tags = { "ProductIntegrationService" })
    @PatchMapping("/{id}/reject")
    public ResponseEntity<Object> rejectProductOrder(@PathVariable("id") UUID productOrderId) {
        service.updateStatusProductOrder(productOrderId, StatusProductOrderEnum.REJECTED);
        return ResponseEntity.accepted().build();
    }
    @Operation(summary = "Complete Product Order", description = "Complete a Product Order", tags = { "ProductIntegrationService" })
    @PatchMapping("/{id}/complete")
    public ResponseEntity<Object> completeProductOrder(@PathVariable("id") UUID productOrderId) {
        service.updateStatusProductOrder(productOrderId, StatusProductOrderEnum.COMPLETED);
        return ResponseEntity.accepted().build();
    }

}
