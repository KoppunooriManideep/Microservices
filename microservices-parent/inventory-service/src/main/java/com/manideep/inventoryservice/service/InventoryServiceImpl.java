package com.manideep.inventoryservice.service;

import com.manideep.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    @Transactional(readOnly = true)
    public boolean isInstock(String skuCode){
        if(inventoryRepository.findBySkuCode(skuCode).isPresent()){
            return inventoryRepository.findBySkuCode(skuCode).get().getQuantity()>0;
        }
        return false;
    }
}
