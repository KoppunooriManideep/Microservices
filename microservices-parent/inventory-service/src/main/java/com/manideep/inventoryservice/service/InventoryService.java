package com.manideep.inventoryservice.service;

import com.manideep.inventoryservice.dto.InventoryResponse;

import java.util.List;

public interface InventoryService {
    InventoryResponse isInstock(List<String> skuCode);
}
