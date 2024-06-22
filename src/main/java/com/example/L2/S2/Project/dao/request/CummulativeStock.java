package com.example.L2.S2.Project.dao.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CummulativeStock {
    private Long productId;
    private String productName;
    private String categoryName;
    private String imageURL;
    private int cumulativeAvailableStock;
    private int cumulativeFullStock;
}