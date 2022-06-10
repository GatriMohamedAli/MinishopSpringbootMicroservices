package com.shopmicroservices.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Orders {
    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(
            name = "sequcen_id_order",
            sequenceName = "sequence_id_order"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_id_order"
    )
    private Long id;
    private String orderNumber;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLineItems> orderLineItemsList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
