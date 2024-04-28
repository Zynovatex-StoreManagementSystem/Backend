package com.example.L2.S2.Project.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Order_Item")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;

    @ManyToOne
    private Order order;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
