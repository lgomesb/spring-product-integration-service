package com.barbosa.ms.productintegrationservice.productintegrationservice.feign.productcatalog;

import com.barbosa.ms.productintegrationservice.productintegrationservice.feign.productcatalog.types.ProductResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.UUID;

@FeignClient(name = "product-catalog", url = "${product-catalog.url}")
public interface ProductCatalogFeign {

    @GetMapping(value = "/product/{id}", produces = "application/json")
    Optional<ProductResponseDTO> findById(@PathVariable("id") UUID id);

}
