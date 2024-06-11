package com.barbosa.ms.productintegrationservice.productintegrationservice.feign.productorder;

import com.barbosa.ms.productintegrationservice.productintegrationservice.feign.dto.request.StatusProductOrderRequestDTO;
import com.barbosa.ms.productintegrationservice.productintegrationservice.feign.productorder.types.ProductOrderRequestDTO;
import com.barbosa.ms.productintegrationservice.productintegrationservice.feign.productorder.types.ProductOrderResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name = "product-order", url = "${product-order.url}")
public interface ProductOrderFeign {

    @PostMapping(value = "/", produces = "application/json")
    ProductOrderResponseDTO create(@RequestBody ProductOrderRequestDTO request);

    @PutMapping(value = "/{id}", produces = "application/json")
    ProductOrderResponseDTO update(@PathVariable UUID id, @RequestBody ProductOrderRequestDTO request);

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, produces = "application/json")
    ProductOrderResponseDTO updateStatus(@PathVariable String id, @RequestBody StatusProductOrderRequestDTO request);

}
