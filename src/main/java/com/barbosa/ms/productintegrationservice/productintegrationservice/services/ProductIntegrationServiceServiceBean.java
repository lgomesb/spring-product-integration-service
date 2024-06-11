package com.barbosa.ms.productintegrationservice.productintegrationservice.services;

import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.records.CreateProductOrderRequestRecord;
import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.records.ProductOrderItemRecord;
import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.records.ProductOrderResponseRecord;
import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.records.UpdateProductOrderRequestRecord;
import com.barbosa.ms.productintegrationservice.productintegrationservice.exception.ProductNotFoundException;
import com.barbosa.ms.productintegrationservice.productintegrationservice.exception.ProductOrderServiceException;
import com.barbosa.ms.productintegrationservice.productintegrationservice.feign.dto.request.StatusProductOrderEnum;
import com.barbosa.ms.productintegrationservice.productintegrationservice.feign.dto.request.StatusProductOrderRequestDTO;
import com.barbosa.ms.productintegrationservice.productintegrationservice.feign.productcatalog.ProductCatalogFeign;
import com.barbosa.ms.productintegrationservice.productintegrationservice.feign.productorder.ProductOrderFeign;
import com.barbosa.ms.productintegrationservice.productintegrationservice.feign.productorder.types.OrderItemDTO;
import com.barbosa.ms.productintegrationservice.productintegrationservice.feign.productorder.types.ProductOrderRequestDTO;
import com.barbosa.ms.productintegrationservice.productintegrationservice.feign.productorder.types.ProductOrderResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductIntegrationServiceServiceBean implements ProductIntegrationServiceService {

    private final ProductCatalogFeign productFeign;
    private final ProductOrderFeign productOrderFeign;
    private final ModelMapper modelMapper;

    public ProductIntegrationServiceServiceBean(ProductCatalogFeign productFeign,
                                                ProductOrderFeign productOrderFeign,
                                                ModelMapper modelMapper) {
        this.productFeign = productFeign;
        this.productOrderFeign = productOrderFeign;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductOrderResponseRecord createProductOrder(CreateProductOrderRequestRecord createProductOrderRequestRecord) {

        ProductOrderRequestDTO request = ProductOrderRequestDTO.builder()
                .description(createProductOrderRequestRecord.description())
                .items(createProductOrderRequestRecord.items()
                        .stream()
                        .map(i -> new OrderItemDTO(this.productIdValidate(i.productId()), i.quantity()))
                        .toList())
                .build();
        ProductOrderResponseDTO response = productOrderFeign.create(request);

        return ProductOrderResponseRecord.builder().description(request.getDescription())
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
    public void approveProductOrder(UUID productOrderId) {
        productOrderFeign.updateStatus(productOrderId.toString(), new StatusProductOrderRequestDTO(StatusProductOrderEnum.ACCEPTED));
    }

    @Override
    public void rejectProductOrder(UUID productOrderId) {
        productOrderFeign.updateStatus(productOrderId.toString(), new StatusProductOrderRequestDTO(StatusProductOrderEnum.REJECT));

    }

    @Override
    public void completeProductOrder(UUID productOrderId) {
        productOrderFeign.updateStatus(productOrderId.toString(), new StatusProductOrderRequestDTO(StatusProductOrderEnum.COMPLETED));

    }

    private UUID productIdValidate(UUID productId) {
        try {
            return productFeign.findById(productId)
                    .orElseThrow(() -> new ProductNotFoundException(
                            String.format("Product not found with id %s.", productId.toString())))
                    .getId();
        } catch (Exception e) {
            throw new ProductOrderServiceException(String.format("Product not found with id %s. " +
                    "Because the error: %s", productId.toString(), e.getMessage()));
        }
    }

}
