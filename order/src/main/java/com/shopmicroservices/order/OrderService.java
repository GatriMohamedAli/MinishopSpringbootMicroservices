package com.shopmicroservices.order;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    public String placeOrder(OrderRequest orderRequest){
        List<OrderLineItems> listOrderLineItems =orderRequest.getOrderLineItemsDTOS()
                .stream()
                .map(this::mapToOrderLineItems)
                .toList();
        Orders order=Orders.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItemsList(listOrderLineItems)
                .build();
        List<String> skueCodes = order.getOrderLineItemsList().stream()
                        .map(OrderLineItems::getSkueCode)
                                .toList();
        InventoryResponse[] responses =webClientBuilder.build().get()
                        .uri("http://inventory/api/v1/inventory",
                                uriBuilder -> uriBuilder.queryParam("skueCode",skueCodes).build())
                        .retrieve()
                        .bodyToMono(InventoryResponse[].class)
                        .block();
        boolean allProductInStock = Arrays.stream(responses).allMatch(InventoryResponse::isInStock);
        if(allProductInStock){
            orderRepository.save(order);
        }else{
            throw new IllegalArgumentException("Products are not all available in stock");
        }
        return "order placed succ";

    }

    public OrderLineItems mapToOrderLineItems(OrderLineItemsDTO dto){
        return OrderLineItems.builder()
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .skueCode(dto.getSkueCode())
                .build();
    }
}
