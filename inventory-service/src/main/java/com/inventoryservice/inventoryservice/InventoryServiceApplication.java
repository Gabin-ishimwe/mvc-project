package com.inventoryservice.inventoryservice;

import com.inventoryservice.inventoryservice.model.InventoryModel;
import com.inventoryservice.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
		return args -> {
			InventoryModel inventoryModel1 = InventoryModel.builder()
					.skuCode("Iphone-12")
					.quantity(2000)
					.build();
			InventoryModel inventoryModel2 = InventoryModel.builder()
					.skuCode("Iphone-11")
					.quantity(3000)
					.build();

			inventoryRepository.save(inventoryModel1);
			inventoryRepository.save(inventoryModel2);
		};
	}

}
