package com.barbosa.ms.productintegrationservice.productintegrationservice.controller;

import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.dto.ProductIntegrationServiceRequestDTO;
import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.dto.ProductIntegrationServiceResponseDTO;
import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.records.ProductIntegrationServiceRecord;
import com.barbosa.ms.productintegrationservice.productintegrationservice.services.ProductIntegrationServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Tag(name = "ProductIntegrationService", description = "Endpoints for ProductIntegrationService operations")
@RestController
@RequestMapping("/")
public class ProductIntegrationServiceController {

    @Autowired
    private ProductIntegrationServiceService service;

    @Operation(summary = "Create ProductIntegrationService", description = "Create a new ProductIntegrationService", tags = { "ProductIntegrationService" })
    @PostMapping
    public ResponseEntity<ProductIntegrationServiceResponseDTO> create(@RequestBody @Valid ProductIntegrationServiceRequestDTO dto) {

        ProductIntegrationServiceRecord productintegrationserviceRecord = service.create(new ProductIntegrationServiceRecord(null, dto.getName()));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(productintegrationserviceRecord.id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Find ProductIntegrationService by Id", description = "Find ProductIntegrationService by id", tags = { "ProductIntegrationService" })
    @GetMapping("{id}")
    public ResponseEntity<ProductIntegrationServiceResponseDTO> findById(@PathVariable("id") String id) {
        ProductIntegrationServiceRecord productintegrationserviceRecord = service.findById(UUID.fromString(id));
        return ResponseEntity.ok().body(ProductIntegrationServiceResponseDTO.builder()
                .id(productintegrationserviceRecord.id())
                .name(productintegrationserviceRecord.name())
                .build());
    }

    @Operation(summary = "Update ProductIntegrationService by Id", description = "Update ProductIntegrationService by id", tags = { "ProductIntegrationService" })
    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable("id") String id, @RequestBody ProductIntegrationServiceRequestDTO dto) {
        service.update(new ProductIntegrationServiceRecord(UUID.fromString(id), dto.getName()));
        return ResponseEntity.accepted().build();
    }

    @Operation(summary = "Delete ProductIntegrationService by Id", description = "Delete ProductIntegrationService by id", tags = { "ProductIntegrationService" })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        service.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "List all ProductIntegrationService", description = "List all ProductIntegrationService in the database", tags = {"ProductIntegrationService"})
    @GetMapping()
    public ResponseEntity<List<ProductIntegrationServiceResponseDTO>> listAll() {
        List<ProductIntegrationServiceResponseDTO> objects = service.listAll()
                .stream()
                .map(ProductIntegrationServiceResponseDTO::create)
                .toList();

        return ResponseEntity.ok(objects);
    }

}
