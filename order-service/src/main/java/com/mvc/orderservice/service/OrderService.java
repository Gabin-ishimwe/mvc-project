package com.mvc.orderservice.service;

import com.mvc.orderservice.dto.InventoryResponse;
import com.mvc.orderservice.dto.OrderLineDto;
import com.mvc.orderservice.dto.OrderRequestDto;
import com.mvc.orderservice.model.OrderLineItems;
import com.mvc.orderservice.model.OrderModel;
import com.mvc.orderservice.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WebClient.Builder webClient;

    public ResponseEntity<?> placeOrder(OrderRequestDto orderRequestDto) {
        List<OrderLineItems> orderLineItems =  orderRequestDto.getOrderLineDto()
                .stream()
                .map((this::mapDtoToRequest))
                .toList();

        OrderModel orderModel = OrderModel.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItems(orderLineItems)
                .build();

        List<String> skuCodes = orderModel.getOrderLineItems().stream().map(OrderLineItems::getSkuCode).toList();

        // call inventory service, and place order if product is in stock
        InventoryResponse[] result = webClient.build().get()
                .uri("http://inventory-service/api/inventory",
                        uriBuilder -> {return uriBuilder.queryParam("skuCode", skuCodes).build();})
                .retrieve()
                // return body type
                .bodyToMono(InventoryResponse[].class)
                // allow webclient to make async calls
                        .block();

        assert result != null;
        List<InventoryResponse> allProductInStock = Arrays.stream(result).filter(InventoryResponse::isInStock).toList();
        System.out.println(allProductInStock);
        System.out.println(Arrays.stream(result).toList());

        if(allProductInStock.size() == result.length && !allProductInStock.isEmpty()) {
            orderRepository.save(orderModel);
            return new ResponseEntity<>("Order placed!", HttpStatus.CREATED);
        }
        throw new IllegalStateException("Product is not available in stock, try again later");

    }

    private OrderLineItems mapDtoToRequest(OrderLineDto orderLineDto) {
        return OrderLineItems.builder()
                .price(orderLineDto.getPrice())
                .skuCode(orderLineDto.getSkuCode())
                .quantity(orderLineDto.getQuantity())
                .build();
    }

    public List<OrderModel> findAllOrders() {
        return orderRepository.findAll();
    }
}
