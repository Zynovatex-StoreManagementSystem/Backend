package com.example.L2.S2.Project.dao.request;

import com.example.L2.S2.Project.model.Stock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseStockRequest {
    private Long id;
    private String location;
    private Long managerId;
    private String managerName;
    private Stock stock;

}
