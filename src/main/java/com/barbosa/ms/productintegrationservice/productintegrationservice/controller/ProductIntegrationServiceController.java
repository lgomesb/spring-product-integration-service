package com.barbosa.ms.productintegrationservice.productintegrationservice.controller;

import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.dto.request.CreateProductOrderRequestDTO;
import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.dto.response.CreateProductOrderResponseDTO;
import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.records.ProductOrderItemRecord;
import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.records.CreateProductOrderRequestRecord;
import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.records.ProductOrderResponseRecord;
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
@RequestMapping("/")
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
    @PatchMapping("/{productOrderId}")
    public ResponseEntity<Object> approveProductOrder(@PathVariable("productOrderId") UUID productOrderId) {
        service.approveProductOrder(productOrderId);
        return ResponseEntity.accepted().build();
    }



}
