package com.barbosa.ms.productintegrationservice.productintegrationservice.services.succeed;

import com.barbosa.ms.productintegrationservice.productintegrationservice.domain.records.ProductOrderResponseRecord;
import com.barbosa.ms.productintegrationservice.productintegrationservice.services.ProductIntegrationServiceServiceBean;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class ProductIntegrationServiceServiceSucceedTest {

    private final Given given = new Given();
    private final When when = new When();
    private final Then then = new Then();

    @InjectMocks
    private ProductIntegrationServiceServiceBean service;
    private ProductOrderResponseRecord productintegrationserviceRecord;

    @Test
    void shouldSuccessWhenCreate() {
    }

    class Given {

        public UUID creationIdOfProductIntegrationService() {
            return UUID.randomUUID();
        }

    }

    class When {

    }

    class Then {

    }
}
