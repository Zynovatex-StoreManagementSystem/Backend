package com.example.L2.S2.Project.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.L2.S2.Project.dao.request.ProductStockSummaryRequest;
import com.example.L2.S2.Project.service.StockService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/stock")
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/summary")
    public List<ProductStockSummaryRequest> getStock() {
        return stockService.getProductStockSummaries();
    }
}
