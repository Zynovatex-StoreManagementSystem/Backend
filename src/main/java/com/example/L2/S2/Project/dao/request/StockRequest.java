package com.example.L2.S2.Project.dao.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockRequest {
    private Long stockId;
    private Long productId;
    private int availableStock;
    private int fullStock;
}
