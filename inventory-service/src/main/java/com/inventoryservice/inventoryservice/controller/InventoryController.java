package com.inventoryservice.inventoryservice.controller;

import com.inventoryservice.inventoryservice.dto.InventoryResponse;
import com.inventoryservice.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public List<InventoryResponse> findInventory(@RequestParam("skuCode") List<String> skuCode) {
        return inventoryService.findInventory(skuCode);
    }
}
