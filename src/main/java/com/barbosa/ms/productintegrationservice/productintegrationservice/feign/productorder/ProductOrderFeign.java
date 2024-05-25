package com.barbosa.ms.productintegrationservice.productintegrationservice.feign.productorder;

import com.barbosa.ms.productintegrationservice.productintegrationservice.feign.productorder.types.ProductOrderRequestDTO;
import com.barbosa.ms.productintegrationservice.productintegrationservice.feign.productorder.types.ProductOrderResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-order", url = "${product-order.url}")
public interface ProductOrderFeign {

    @PostMapping(value = "/", produces = "application/json")
    ProductOrderResponseDTO create(@RequestBody ProductOrderRequestDTO request);

}
