package com.example.L2.S2.Project.dao.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductStockSummaryRequest {
    private Long productId;
    private String productName;
    private String categoryName;
    private String imageURL;
    private Long fullStock;
    private Long availableStock;
}
