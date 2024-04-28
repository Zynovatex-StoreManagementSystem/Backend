package com.example.L2.S2.Project.dao.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private String name;
    private String category;
    private double price;
    private double weight;
    private String imageUrl;
}
