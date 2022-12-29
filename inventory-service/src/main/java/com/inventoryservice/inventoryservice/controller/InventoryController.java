package com.inventoryservice.inventoryservice.controller;

import com.inventoryservice.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public boolean findInventory(@RequestParam("skuCode") String skuCode) {
        System.out.println(skuCode);
        return inventoryService.findInventory(skuCode);
    }
}
