package com.mvc.orderservice.repository;

import com.mvc.orderservice.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {
}
