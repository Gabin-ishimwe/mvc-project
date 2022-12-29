package com.mvc.orderservice.service;

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

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public ResponseEntity<?> placeOrder(OrderRequestDto orderRequestDto) {
        List<OrderLineItems> orderLineItems =  orderRequestDto.getOrderLineDto()
                .stream()
                .map((this::mapDtoToRequest))
                .toList();

        OrderModel orderModel = OrderModel.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItems(orderLineItems)
                .build();
        System.out.println(orderModel.toString());
        orderRepository.save(orderModel);
        return new ResponseEntity<>("Order placed!", HttpStatus.CREATED);
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
