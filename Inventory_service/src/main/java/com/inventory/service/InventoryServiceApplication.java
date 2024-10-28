package com.inventory.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.inventory.service.model.Inventory;
import com.inventory.service.repository.InventoryRepository;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient


public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
		
		
	
	}
	 @Bean
	    public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
	        return args -> {
	            Inventory inventory = new Inventory();
	            inventory.setSkuCode("iphone_13");
	            inventory.setQuantity(100);

	            Inventory inventory1 = new Inventory();
	            inventory1.setSkuCode("iphone_13_red");
	            inventory1.setQuantity(0);

	            // Save these inventories to the repository
	            inventoryRepository.save(inventory);
	            inventoryRepository.save(inventory1);
	        };
	    }

}
