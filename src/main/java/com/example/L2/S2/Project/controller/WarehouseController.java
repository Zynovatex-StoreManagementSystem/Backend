package com.example.L2.S2.Project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.L2.S2.Project.dao.request.StockRequest;
import com.example.L2.S2.Project.dao.request.WarehouseRequest;
import com.example.L2.S2.Project.dao.response.StockResponse;
import com.example.L2.S2.Project.model.Stock;
import com.example.L2.S2.Project.model.Warehouse;
import com.example.L2.S2.Project.service.StockService;
import com.example.L2.S2.Project.service.WarehouseService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/warehouse")
public class WarehouseController {
    private final StockService stockService;
    private final WarehouseService warehouseService;

    public WarehouseController(StockService stockService, WarehouseService warehouseService) {
        this.stockService = stockService;
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public List<WarehouseRequest> getAllWarehouses() {
        return warehouseService.getAllWarehouseRequests();
    }

    @PostMapping
    public Warehouse addWarehouse(@RequestBody Warehouse warehouse) {
        return warehouseService.addWarehouse(warehouse);
    }

    @GetMapping("/{warehouseId}")
    public WarehouseRequest getStockById(@PathVariable Long warehouseId) {
        return warehouseService.getWarehouseRequestById(warehouseId);
    }

    @PutMapping("/{warehouseId}")
    public Warehouse updateWarehouseById(@PathVariable Long warehouseId, @RequestBody WarehouseRequest warehouse) {
        return warehouseService.updateWarehouseById(warehouseId, warehouse);
    }

    @DeleteMapping("/{warehouseId}")
    public boolean deleteWarehouseById(@PathVariable Long warehouseId) {
        return warehouseService.deleteWarehouseById(warehouseId);
    }

    @GetMapping("/{warehouseId}/stock")
    public List<StockResponse> getStocksByWarehouse(@PathVariable Long warehouseId) {
        return stockService.getStockByWarehouseId(warehouseId);
    }

    @PostMapping("/{warehouseId}/stock")
    public StockResponse addStock(@PathVariable Long warehouseId, @RequestBody StockRequest stock) {
        return stockService.saveStock(warehouseId, stock);
        
    }

    @PatchMapping("/{warehouseId}/stock/{stockId}")
    public Stock updateStock(@PathVariable Long warehouseId, @PathVariable Long stockId, @RequestBody StockRequest stock) {
        System.out.println("warehouseId: " + warehouseId + "\nstockId: " + stockId + "\nstock: " + stock);
        return null;
        //return stockService.updateStock(warehouseId, stockId, stock);
    }

    @DeleteMapping("/{warehouseId}/stock/{stockId}")
    public boolean deleteStock(@PathVariable Long warehouseId, @PathVariable Long stockId) {
        return stockService.deleteStockById(stockId);
    }
}
