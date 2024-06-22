package com.example.L2.S2.Project.dao.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockResponse {
    private Long stockId;
    private Long productId;
    private String productName;
    private String productIcon;
    private int availableStock;
    private int fullStock;
}
