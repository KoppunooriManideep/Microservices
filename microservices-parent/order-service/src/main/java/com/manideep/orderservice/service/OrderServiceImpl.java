package com.manideep.orderservice.service;

import com.manideep.orderservice.dto.OrderLineItemsDto;
import com.manideep.orderservice.dto.OrderRequest;
import com.manideep.orderservice.model.Order;
import com.manideep.orderservice.model.OrderLineItems;
import com.manideep.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void placeOrder(OrderRequest orderRequest){
        Order order =new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItemsList= orderRequest.getOrderLineItemsDtoList().stream()
                .map(this::mapToOrder).collect(Collectors.toList());
        order.setOrderLineItemsList(orderLineItemsList);

        orderRepository.save(order);
    }

    private OrderLineItems mapToOrder(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        return orderLineItems;
    }
}
