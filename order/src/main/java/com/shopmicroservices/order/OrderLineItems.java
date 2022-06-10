package com.shopmicroservices.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class OrderLineItems {
    @Id
    @SequenceGenerator(
            name = "sequcen_id_orderline",
            sequenceName = "sequence_id_orderline"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_id_orderline"
    )
    private long id;
    private String skueCode;
    private BigDecimal price;
    private Integer quantity;

}
