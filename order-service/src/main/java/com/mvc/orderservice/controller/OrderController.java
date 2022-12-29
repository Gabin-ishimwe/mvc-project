package com.mvc.orderservice.controller;

import com.mvc.orderservice.dto.OrderRequestDto;
import com.mvc.orderservice.model.OrderModel;
import com.mvc.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return orderService.placeOrder(orderRequestDto);
    }

    @GetMapping
    public List<OrderModel> findAllOrders() {
        return orderService.findAllOrders();
    }
}
