package com.manideep.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
public class InventoryResponse {
    private List<InventoryDetails> inventoryDetailsList;
}
