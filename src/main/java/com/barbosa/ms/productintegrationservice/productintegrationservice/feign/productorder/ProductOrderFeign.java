package com.barbosa.ms.productintegrationservice.productintegrationservice.feign.productorder;

import com.barbosa.ms.productintegrationservice.productintegrationservice.feign.productorder.types.ProductOrderRequestDTO;
import com.barbosa.ms.productintegrationservice.productintegrationservice.feign.productorder.types.ProductOrderResponseDTO;
import com.barbosa.ms.productintegrationservice.productintegrationservice.feign.productorder.types.StatusProductOrderRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name = "product-order", url = "${product-order.url}")
public interface ProductOrderFeign {

    @PostMapping(value = "/", produces = "application/json")
    ProductOrderResponseDTO create(@RequestBody ProductOrderRequestDTO request);

    @GetMapping(value = "/{id}", produces = "application/json")
    ProductOrderResponseDTO findById(@PathVariable UUID id);

    @PutMapping(value = "/{id}", produces = "application/json")
    ProductOrderResponseDTO update(@PathVariable UUID id, @RequestBody ProductOrderRequestDTO request);

    @PatchMapping(value = "/{id}", produces = "application/json")
    ResponseEntity<ProductOrderResponseDTO> updateStatus(@PathVariable String id,
                                                         @RequestBody StatusProductOrderRequestDTO request);

}
