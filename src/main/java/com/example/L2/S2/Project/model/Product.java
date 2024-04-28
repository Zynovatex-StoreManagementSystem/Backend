package com.example.L2.S2.Project.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private int price;
    private int quantity;
    private String brand;
    private String imageUrl;
    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;
    private LocalDateTime createdAt;

}


