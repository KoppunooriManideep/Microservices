package com.manideep.inventoryservice.controller;

import com.manideep.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/inventory/")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("instockStatus/{sku-code}")
    public ResponseEntity<Boolean> isInstock(@PathVariable(value = "sku-code") String skuCode){

        return new ResponseEntity<>(inventoryService.isInstock(skuCode), HttpStatus.OK);
    }
}
