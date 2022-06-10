package com.shopmicroservices.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItemsDTO {
    private String skueCode;
    private BigDecimal price;
    private Integer quantity;
}
