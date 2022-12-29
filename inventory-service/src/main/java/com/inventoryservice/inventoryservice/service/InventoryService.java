package com.inventoryservice.inventoryservice.service;

import com.inventoryservice.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;
    public boolean findInventory(String skuCode) {
        var inventory = inventoryRepository.findBySkuCode(skuCode);
        return inventory.isPresent();
    }
}
