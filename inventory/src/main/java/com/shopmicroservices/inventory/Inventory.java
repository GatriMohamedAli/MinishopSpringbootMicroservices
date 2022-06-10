package com.shopmicroservices.inventory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Inventory {
    @Id
    @SequenceGenerator(
            name = "sequence_id_inventory",
            sequenceName = "sequence_id_inventory"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_id_inventory"
    )
    private long id;
    private String skueCode;
    private Integer quantity;
}
