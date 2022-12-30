package com.inventoryservice.inventoryservice.service;

import com.inventoryservice.inventoryservice.dto.InventoryResponse;
import com.inventoryservice.inventoryservice.model.InventoryModel;
import com.inventoryservice.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public List<InventoryResponse> findInventory(List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode)
                .stream()
                .map(inventoryModel -> {
                    return InventoryResponse.builder()
                            .skuCode(inventoryModel.getSkuCode())
                            .isInStock(inventoryModel.getQuantity() > 0)
                            .build();
                }).toList();
    }
}
