package com.manideep.inventoryservice.controller;

import com.manideep.inventoryservice.dto.InventoryDetails;
import com.manideep.inventoryservice.dto.InventoryResponse;
import com.manideep.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/inventory/")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("instockStatus")
    public ResponseEntity<InventoryResponse> isInstock(@RequestParam(value = "skuCode") List<String> skuCode){

        return new ResponseEntity<>(inventoryService.isInstock(skuCode), HttpStatus.OK);
    }
}
