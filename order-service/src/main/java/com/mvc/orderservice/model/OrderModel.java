package com.mvc.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(
        name = "orders"
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderModel {
    @Id
    @GeneratedValue
    private Long id;
    private String orderNumber;
    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "order_items_id",
            referencedColumnName = "id"
    )
    private List<OrderLineItems> orderLineItems;
}
