package com.pedro.microservices.inventory.controller;

import com.pedro.microservices.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public boolean inStock(@RequestParam String skuCode, @RequestParam Integer quantity) {
        return inventoryService.inStock(skuCode, quantity);
    }
}
