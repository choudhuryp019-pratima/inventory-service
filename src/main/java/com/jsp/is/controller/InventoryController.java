package com.jsp.is.controller;

import com.jsp.is.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/check")
    public boolean checkStock(@RequestParam String productName,
                              @RequestParam int quantity) {
        return inventoryService.isInStock(productName, quantity);
    }

    @PostMapping("/reduce")
    public String reduceStock(@RequestParam String productName,
                             @RequestParam int quantity) {
        inventoryService.reduceStock(productName, quantity);
        return "Stock updated";
    }
}