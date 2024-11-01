package com.programmingtechie.orderservice.service;

import com.programmingtechie.orderservice.dto.InventoryResponse;
import com.programmingtechie.orderservice.dto.OrderLineItemsDto;
import com.programmingtechie.orderservice.dto.OrderRequest;
import com.programmingtechie.orderservice.event.OrderPlacedEvent;
import com.programmingtechie.orderservice.model.Order;
import com.programmingtechie.orderservice.model.OrderLineItems;
import com.programmingtechie.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

//@Service
//@RequiredArgsConstructor
//@Transactional
//public class OrderService {
//
//    private final OrderRepository orderRepository;
//    private final WebClient webClient;
//
//    public void placeOrder(OrderRequest orderRequest) {
//        Order order = new Order();
//        order.setOrderNumber(UUID.randomUUID().toString());
//
//        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
//                .stream()
//                .map(this::mapToDto)
//                .toList();
//
//        order.setOrderLineItemsList(orderLineItems);
//
//        List<String> skuCodes = order.getOrderLineItemsList().stream()
//                .map(OrderLineItems::getSkuCode)
//                .toList();
//
//        // Call Inventory Service, and place order if product is in
//        // stock
//        System.out.println("api started");
//        InventoryResponse[] inventoryResponsArray = webClient.get()
//                .uri("http://inventory-service/api/inventory",
//                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
//                .retrieve()
//                .bodyToMono(InventoryResponse[].class)
//                .block();
//
//        boolean allProductsInStock = Arrays.stream(inventoryResponsArray)
//                .allMatch(InventoryResponse::isInStock);
//
//        if(allProductsInStock){
//            orderRepository.save(order);
//        } else {
//            throw new IllegalArgumentException("Product is not in stock, please try again later");
//        }
//    }
//
//    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
//        OrderLineItems orderLineItems = new OrderLineItems();
//        orderLineItems.setPrice(orderLineItemsDto.getPrice());
//        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
//        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
//        return orderLineItems;
//    }
//}
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class OrderService {
//
//    private final OrderRepository orderRepository;
//    private final WebClient.Builder webClientBuilder;  // Use WebClient.Builder
//
//    public void placeOrder(OrderRequest orderRequest) {
//        Order order = new Order();
//        order.setOrderNumber(UUID.randomUUID().toString());
//
//        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
//                .stream()
//                .map(this::mapToDto)
//                .toList();
//
//        order.setOrderLineItemsList(orderLineItems);
//
//        List<String> skuCodes = order.getOrderLineItemsList().stream()
//                .map(OrderLineItems::getSkuCode)
//                .toList();
//
//        // Call Inventory Service, and place order if product is in stock
//        System.out.println("api started");
//        InventoryResponse[] inventoryResponsArray = webClientBuilder.build()  // Use webClientBuilder here
//                .get()
//                .uri("http://inventory-service/api/inventory",  // This will use Eureka load balancing
//                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
//                .retrieve()
//                .bodyToMono(InventoryResponse[].class)
//                .block();
//
//        boolean allProductsInStock = Arrays.stream(inventoryResponsArray)
//                .allMatch(InventoryResponse::isInStock);
//
//        if (allProductsInStock) {
//            orderRepository.save(order);
//        } else {
//            throw new IllegalArgumentException("Product is not in stock, please try again later");
//        }
//    }
//
//    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
//        OrderLineItems orderLineItems = new OrderLineItems();
//        orderLineItems.setPrice(orderLineItemsDto.getPrice());
//        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
//        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
//        return orderLineItems;
//    }
//}
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class OrderService {
//
//    private final OrderRepository orderRepository;
//    private final WebClient.Builder webClientBuilder;  // Use WebClient.Builder
//
//    @LoadBalanced  // Ensure load balancing for WebClient
//    public WebClient.Builder loadBalancedWebClientBuilder() {
//        return WebClient.builder();
//    }
//
//    public void placeOrder(OrderRequest orderRequest) {
//        Order order = new Order();
//        order.setOrderNumber(UUID.randomUUID().toString());
//
//        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
//                .stream()
//                .map(this::mapToDto)
//                .toList();
//
//        order.setOrderLineItemsList(orderLineItems);
//
//        List<String> skuCodes = order.getOrderLineItemsList().stream()
//                .map(OrderLineItems::getSkuCode)
//                .toList();
//
//        // Call Inventory Service, and place order if product is in stock
//        System.out.println("api started");
//        InventoryResponse[] inventoryResponsArray = webClientBuilder.build()  // Use webClientBuilder here
//                .get()
//                .uri("http://inventory-service/api/inventory",  // This will use Eureka load balancing
//                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
//                .retrieve()
//                .bodyToMono(InventoryResponse[].class)
//                .block();
//
//        boolean allProductsInStock = Arrays.stream(inventoryResponsArray)
//                .allMatch(InventoryResponse::isInStock);
//
//        if (allProductsInStock) {
//            orderRepository.save(order);
//        } else {
//            throw new IllegalArgumentException("Product is not in stock, please try again later");
//        }
//    }
//
//    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
//        OrderLineItems orderLineItems = new OrderLineItems();
//        orderLineItems.setPrice(orderLineItemsDto.getPrice());
//        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
//        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
//        return orderLineItems;
//    }
//}
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class OrderService {
//    private final OrderRepository orderRepository;
//    private final WebClient.Builder webClientBuilder; // Use WebClient.Builder
//
//    public void placeOrder(OrderRequest orderRequest) {
//        Order order = new Order();
//        order.setOrderNumber(UUID.randomUUID().toString());
//
//        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
//                .stream()
//                .map(this::mapToDto)
//                .toList();
//
//        order.setOrderLineItemsList(orderLineItems);
//
//        List<String> skuCodes = order.getOrderLineItemsList().stream()
//                .map(OrderLineItems::getSkuCode)
//                .toList();
//
//        // Call Inventory Service using Eureka service name
//        InventoryResponse[] inventoryResponseArray = webClientBuilder.build()
//                .get()
//                .uri("http://inventory-service/api/inventory",
//                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
//                .retrieve()
//                .bodyToMono(InventoryResponse[].class)
//                .block();
//
//        boolean allProductsInStock = Arrays.stream(inventoryResponseArray)
//                .allMatch(InventoryResponse::isInStock);
//
//        if (allProductsInStock) {
//            orderRepository.save(order);
//        } else {
//            throw new IllegalArgumentException("Product is not in stock, please try again later");
//        }
//    }
//
//    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
//        OrderLineItems orderLineItems = new OrderLineItems();
//        orderLineItems.setPrice(orderLineItemsDto.getPrice());
//        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
//        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
//        return orderLineItems;
//    }
//}
@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    private final KafkaTemplate<String,OrderPlacedEvent> kafkaTemplate;

    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }

    public String placeOrder(OrderRequest orderRequest) {
        try {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());

            List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                    .stream()
                    .map(this::mapToDto)
                    .toList();

            order.setOrderLineItemsList(orderLineItems);

            List<String> skuCodes = order.getOrderLineItemsList().stream()
                    .map(OrderLineItems::getSkuCode)
                    .toList();

            // Call Inventory Service, and place order if product is in stock
            InventoryResponse[] inventoryResponseArray = webClientBuilder.build()
                    .get()
                    .uri("http://inventory-service/api/inventory",
                            uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                    .retrieve()
                    .bodyToMono(InventoryResponse[].class)
                    .block();

            boolean allProductsInStock = Arrays.stream(inventoryResponseArray)
                    .allMatch(InventoryResponse::isInStock);

            if (allProductsInStock) {
                orderRepository.save(order);
                
                kafkaTemplate.send("notificationtopic", new OrderPlacedEvent(order.getOrderNumber()));
                
                
                return "Order placed successfully";
               
            } else {
                throw new IllegalArgumentException("Product is not in stock, please try again later");
            }
        } catch (Exception e) {
            // Log the error
            System.out.println("Error occurred while placing order: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}