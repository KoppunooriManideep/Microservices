package com.manideep.orderservice.service;

import com.manideep.orderservice.dto.InventoryDetails;
import com.manideep.orderservice.dto.InventoryResponse;
import com.manideep.orderservice.dto.OrderLineItemsDto;
import com.manideep.orderservice.dto.OrderRequest;
import com.manideep.orderservice.model.Order;
import com.manideep.orderservice.model.OrderLineItems;
import com.manideep.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public void placeOrder(OrderRequest orderRequest){
        Order order =new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItemsList= orderRequest.getOrderLineItemsDtoList().stream()
                .map(this::mapToOrder).collect(Collectors.toList());
        order.setOrderLineItemsList(orderLineItemsList);

        List<String> skuCodesList = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode).collect(Collectors.toList());

        //calling invenory Microservice tocheck if item is in the stock
        InventoryResponse response = webClientBuilder.build().get()
                .uri("http://inventory-service/v1/inventory/instockStatus",
                        uriBuilder -> uriBuilder.queryParam("skuCode",skuCodesList).build())
                .retrieve()
                .bodyToMono(InventoryResponse.class)
                .block();
        boolean allProductsInstock = response.getInventoryDetailsList().stream().allMatch(InventoryDetails::isInStock);
        if(allProductsInstock){
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("One or More Products are out of stock");
        }

    }

    private OrderLineItems mapToOrder(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        return orderLineItems;
    }
}
