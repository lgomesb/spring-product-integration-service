package com.barbosa.ms.productintegrationservice.productintegrationservice.feign.productcatalog;

<<<<<<< HEAD:src/main/java/com/barbosa/ms/productintegrationservice/productintegrationservice/feign/productcatalog/ProductCatalogFeign.java
import com.barbosa.ms.productintegrationservice.productintegrationservice.feign.productcatalog.types.ProductResponseDTO;
=======
import com.barbosa.ms.productintegrationservice.productintegrationservice.feign.dto.request.ProductRequestDTO;
import com.barbosa.ms.productintegrationservice.productintegrationservice.feign.dto.response.ProductResponseDTO;
>>>>>>> 8a1edaeb98666a704c4bd965d293aa64b204bc54:src/main/java/com/barbosa/ms/productintegrationservice/productintegrationservice/feign/ProductCatalogMgmtClient.java
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
import java.util.UUID;

@FeignClient(name = "product-catalog", url = "${product-catalog.url}")
public interface ProductCatalogFeign {


    @GetMapping(value = "/product/{id}", produces = "application/json")
    Optional<ProductResponseDTO> findById(@PathVariable("id") UUID id);

    @PostMapping(value = "/product", produces = "application/json")
    ProductResponseDTO create(@RequestBody ProductRequestDTO dto);

}
