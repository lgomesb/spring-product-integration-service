package com.barbosa.ms.productintegrationservice.productintegrationservice.services;

import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.records.CreateProductOrderRequestRecord;
import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.records.ProductOrderResponseRecord;
import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.records.UpdateProductOrderRequestRecord;

import java.util.UUID;

public interface ProductIntegrationServiceService {

    ProductOrderResponseRecord createProductOrder(CreateProductOrderRequestRecord createProductOrderRequestRecord);

    void updateProductOrder(UpdateProductOrderRequestRecord updateProductOrderRequestRecord);

    void approveProductOrder(UUID productOrderId);

    void rejectProductOrder(UUID productOrderId);

    void completeProductOrder(UUID productOrderId);

}
