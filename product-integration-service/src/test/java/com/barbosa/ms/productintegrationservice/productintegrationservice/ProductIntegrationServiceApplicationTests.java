package com.barbosa.ms.productintegrationservice.productintegrationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ActiveProfiles;

@SpringBootApplication
@ActiveProfiles(value = "test")
public class ProductIntegrationServiceApplicationTests {

	public static void main(String[] args) {
		SpringApplication.run(ProductIntegrationServiceApplicationTests.class, args);
	}

}
