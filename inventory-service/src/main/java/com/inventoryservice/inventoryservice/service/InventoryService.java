package com.inventoryservice.inventoryservice.service;

import com.inventoryservice.inventoryservice.dto.InventoryResponse;
import com.inventoryservice.inventoryservice.model.InventoryModel;
import com.inventoryservice.inventoryservice.repository.InventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public List<InventoryResponse> findInventory(List<String> skuCode) throws InterruptedException {
//        log.info("Wait started");
//        Thread.sleep(10000);
//        log.info("Wait finished");
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
