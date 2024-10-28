package com.product.service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.service.dto.ProductRequest;
import com.product.service.dto.ProductResponse;
import com.product.service.model.Product;
import com.product.service.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {
	@Autowired
	private  ProductRepository productRepository;
	
	public void createProduct(ProductRequest productRequest) {
		Product product=Product.builder()
				        .name(productRequest.getName())
				        .description(productRequest.getDescription())
				        .price(productRequest.getPrice())
				        .build();
		
		productRepository.save(product);
		log.info("Product {} is saved",product.getId());
	}
	
	
	  public List<ProductResponse> getAllProducts() {
	        List<Product> products = productRepository.findAll();

	        // Use a lambda expression to pass the product to mapToProductResponse
	        return products.stream()
	                .map(this::mapToProductResponse)  // Use method reference
	                .collect(Collectors.toList());  // Collect results into a list
	    }

	    private ProductResponse mapToProductResponse(Product product) {
	        return ProductResponse.builder()
	                .id(product.getId())
	                .name(product.getName())
	                .description(product.getDescription())
	                .price(product.getPrice())
	                .build();
	    }
	}
	
	



