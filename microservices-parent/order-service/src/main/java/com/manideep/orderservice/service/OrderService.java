package com.manideep.orderservice.service;

import com.manideep.orderservice.dto.OrderRequest;

public interface OrderService {
    void placeOrder(OrderRequest orderRequest);
}
