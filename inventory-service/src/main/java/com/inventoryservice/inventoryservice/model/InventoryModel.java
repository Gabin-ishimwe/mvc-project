package com.inventoryservice.inventoryservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "inventories")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryModel {
    @Id
    @GeneratedValue
    private Long id;
    private String skuCode;
    private Integer quantity;
}
