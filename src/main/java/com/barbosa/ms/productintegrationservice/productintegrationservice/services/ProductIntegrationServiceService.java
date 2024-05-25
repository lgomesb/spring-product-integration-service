package com.barbosa.ms.productintegrationservice.productintegrationservice.services;

import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.records.ProductOrderRequestRecord;
import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.records.ProductOrderResponseRecord;

public interface ProductIntegrationServiceService {

    ProductOrderResponseRecord createProductOrder(ProductOrderRequestRecord productOrderRequestRecord);

}
