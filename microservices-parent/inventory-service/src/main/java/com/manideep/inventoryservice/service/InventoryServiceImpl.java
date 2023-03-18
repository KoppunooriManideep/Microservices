package com.manideep.inventoryservice.service;

import com.manideep.inventoryservice.dto.InventoryDetails;
import com.manideep.inventoryservice.dto.InventoryResponse;
import com.manideep.inventoryservice.model.Inventory;
import com.manideep.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    @Transactional(readOnly = true)
    public InventoryResponse isInstock(List<String> skuCode){
        List<InventoryDetails> inventoryDetailsList = inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(this::getInventoryInfo).collect(Collectors.toList());
        return InventoryResponse.builder().inventoryDetailsList(inventoryDetailsList).build();
    }

    private InventoryDetails getInventoryInfo(Inventory inventory) {
        return InventoryDetails.builder()
                .skuCode(inventory.getSkuCode())
                .isInStock(inventory.getQuantity()>0).build();
    }
}
