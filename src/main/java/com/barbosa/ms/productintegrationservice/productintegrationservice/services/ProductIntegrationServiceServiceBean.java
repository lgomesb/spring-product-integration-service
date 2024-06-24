package com.barbosa.ms.productintegrationservice.productintegrationservice.services;

import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.records.CreateProductOrderRequestRecord;
import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.records.ProductOrderItemRecord;
import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.records.ProductOrderResponseRecord;
import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.records.UpdateProductOrderRequestRecord;
import com.barbosa.ms.productintegrationservice.productintegrationservice.exception.ProductNotFoundException;
import com.barbosa.ms.productintegrationservice.productintegrationservice.exception.ProductOrderServiceException;
import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.enums.StatusProductOrderEnum;
import com.barbosa.ms.productintegrationservice.productintegrationservice.feign.productinvetory.ProductInvetoryFeign;
import com.barbosa.ms.productintegrationservice.productintegrationservice.feign.productinvetory.types.ProductInventoryRequestDTO;
import com.barbosa.ms.productintegrationservice.productintegrationservice.feign.productorder.types.StatusProductOrderRequestDTO;
import com.barbosa.ms.productintegrationservice.productintegrationservice.feign.productcatalog.ProductCatalogFeign;
import com.barbosa.ms.productintegrationservice.productintegrationservice.feign.productorder.ProductOrderFeign;
import com.barbosa.ms.productintegrationservice.productintegrationservice.feign.productorder.types.OrderItemDTO;
import com.barbosa.ms.productintegrationservice.productintegrationservice.feign.productorder.types.ProductOrderRequestDTO;
import com.barbosa.ms.productintegrationservice.productintegrationservice.feign.productorder.types.ProductOrderResponseDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.record.RecordModule;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.logging.Logger;

@Service
public class ProductIntegrationServiceServiceBean implements ProductIntegrationServiceService {

    public static final Logger LOGGER = Logger.getLogger(ProductIntegrationServiceServiceBean.class.getName());
    public static final String PRODUCT_NOT_FOUND_WITH_ID = "Product not found with id %s.";

    private final ProductCatalogFeign productFeign;
    private final ProductOrderFeign productOrderFeign;
    private final ProductInvetoryFeign productInvetoryFeign;
    private final ModelMapper modelMapper;

    public ProductIntegrationServiceServiceBean(ProductCatalogFeign productFeign,
                                                ProductOrderFeign productOrderFeign,
                                                ProductInvetoryFeign productInvetoryFeign,
                                                ModelMapper modelMapper) {
        this.productFeign = productFeign;
        this.productOrderFeign = productOrderFeign;
        this.productInvetoryFeign = productInvetoryFeign;
        this.modelMapper = modelMapper.registerModule(new RecordModule());
    }

    @Override
    public ProductOrderResponseRecord createProductOrder(CreateProductOrderRequestRecord createProductOrderRequestRecord) {

        ProductOrderRequestDTO request = modelMapper.map(createProductOrderRequestRecord, ProductOrderRequestDTO.class);
        ProductOrderResponseDTO response = productOrderFeign.create(request);

        return ProductOrderResponseRecord.builder()
                .description(request.getDescription())
                .id(response.getId())
                .items(response.getItems()
                        .stream()
                        .map(i -> new ProductOrderItemRecord(i.getProductId(), i.getQuantity()))
                        .toList())
                .build();

    }

    @Override
    public void updateProductOrder(UpdateProductOrderRequestRecord updateProductOrderRequestRecord) {
        throw new UnsupportedOperationException("Method is not implemented.");
    }

    @Override
    public void updateStatusProductOrder(UUID productOrderId, StatusProductOrderEnum status) {
        ResponseEntity<ProductOrderResponseDTO> response = productOrderFeign.updateStatus(productOrderId.toString(),
                new StatusProductOrderRequestDTO(status));

        if(StatusProductOrderEnum.COMPLETED.equals(status)) {
            ProductOrderResponseDTO productOrder = productOrderFeign.findById(productOrderId);
            productOrder.getItems().forEach(i -> this.addProductInventory(productOrderId, i));
        }

        LOGGER.info("STATUS CODE:" + response.getStatusCode());
    }

    private void addProductInventory(UUID productOrderId, OrderItemDTO orderItemDTO) {
        productInvetoryFeign.create(ProductInventoryRequestDTO.builder()
                .productOrderId(productOrderId)
                .productId(orderItemDTO.getProductId())
                .quantity(orderItemDTO.getQuantity())
                .build());
    }

    private UUID productIdValidate(UUID productId) {
        try {
            return productFeign.findById(productId)
                    .orElseThrow(() -> new ProductNotFoundException(
                            String.format(PRODUCT_NOT_FOUND_WITH_ID, productId.toString())))
                    .getId();
        } catch (Exception e) {
            throw new ProductOrderServiceException(String.format(PRODUCT_NOT_FOUND_WITH_ID +
                    "Because the error: %s", productId.toString(), e.getMessage()));
        }
    }

}
