package com.barbosa.ms.productintegrationservice.productintegrationservice.feign;

import com.barbosa.ms.productintegrationservice.productintegrationservice.feign.dto.request.ProductRequestDTO;
import com.barbosa.ms.productintegrationservice.productintegrationservice.feign.dto.response.ProductResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "product-catalog-mgmt", url = "http://localhost:8080/product-mgmt")
public interface ProductCatalogMgmtClient {


    @GetMapping(value = "/product/{id}", produces = "application/json")
    ProductResponseDTO getProduct(@PathVariable("id") UUID id);

    @PostMapping(value = "/product", produces = "application/json")
    ProductResponseDTO create(@RequestBody ProductRequestDTO dto);

}
