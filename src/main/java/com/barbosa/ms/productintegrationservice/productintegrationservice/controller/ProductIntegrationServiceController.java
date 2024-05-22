package com.barbosa.ms.productintegrationservice.productintegrationservice.controller;

import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.dto.ProductIntegrationServiceRequestDTO;
import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.dto.ProductIntegrationServiceResponseDTO;
import com.barbosa.ms.productintegrationservice.productintegrationservice.services.ProductIntegrationServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "ProductIntegrationService", description = "Endpoints for ProductIntegrationService operations")
@RestController
@RequestMapping("/")
public class ProductIntegrationServiceController {

    @Autowired
    private ProductIntegrationServiceService service;

    @Operation(summary = "Create ProductIntegrationService", description = "Create a new ProductIntegrationService", tags = { "ProductIntegrationService" })
    @PostMapping
    public ResponseEntity<ProductIntegrationServiceResponseDTO> create(@RequestBody @Valid ProductIntegrationServiceRequestDTO dto) {

//        ProductIntegrationServiceRecord productintegrationserviceRecord = service.create(new ProductIntegrationServiceRecord(null, dto.getName()));
//
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("{id}")
//                .buildAndExpand(productintegrationserviceRecord.id())
//                .toUri();
//        return ResponseEntity.created(location).build();
        return null;
    }



}
