package com.barbosa.ms.productintegrationservice.productintegrationservice.feign.productinvetory;

import com.barbosa.ms.productintegrationservice.productintegrationservice.feign.productinvetory.types.ProductInventoryRequestDTO;
import com.barbosa.ms.productintegrationservice.productintegrationservice.feign.productinvetory.types.ProductInventoryResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-inventory", url = "${product-inventory.url}")
public interface ProductInvetoryFeign {

    @PostMapping(value = "/", produces = "application/json")
    ProductInventoryResponseDTO create(@RequestBody ProductInventoryRequestDTO request);

}
