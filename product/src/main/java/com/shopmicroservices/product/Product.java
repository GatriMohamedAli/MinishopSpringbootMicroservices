package com.shopmicroservices.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Product {
    @Id
    @SequenceGenerator(
            name = "sequence_id_product",
            sequenceName = "sequence_id_product"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_id_product"
    )
    private long id;
    private String name;
    private String description;
    private BigDecimal price;
}
