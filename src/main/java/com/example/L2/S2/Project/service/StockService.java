package com.example.L2.S2.Project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.L2.S2.Project.dao.request.ProductStockSummaryRequest;
import com.example.L2.S2.Project.dao.request.StockRequest;
import com.example.L2.S2.Project.dao.response.StockResponse;
import com.example.L2.S2.Project.model.Stock;
import com.example.L2.S2.Project.model.Warehouse;
import com.example.L2.S2.Project.repository.StockRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StockService {

    private final StockRepository stockRepository;
    private final WarehouseService warehouseService;
    private final ProductService productService;

    public Stock getStockById(Long id) {
        return stockRepository.findById(id).orElse(null);
    }

    public Stock updateStockById(Long id, Stock stock) {
        if (stockRepository.findById(id).isPresent()) {
            stock.setId(id);
            return stockRepository.save(stock);
        }
        return null;
    }

    public boolean deleteStockById(Long id) {
        if (stockRepository.findById(id).isPresent()) {
            stockRepository.deleteById(id);
            return true;
        }
        return false;

    }

    public List<StockResponse> getStockByWarehouseId(Long warehouseId) {
        return stockRepository.findStockByWarehouseId(warehouseId);
    }

    public List<Stock> getStockByProductId(Long productId) {
        return stockRepository.findByProductId(productId);
    }

    public Stock getStockByProductIdAndWarehouseId(Long productId, Long warehouseId) {
        return stockRepository.findByProductIdAndWarehouseId(productId, warehouseId);
    }

    public List<ProductStockSummaryRequest> getProductStockSummaries() {
        return stockRepository.findProductStockSummaries();
    }

    public StockResponse saveStock(Long warehouseId, StockRequest stock) {
        if (stock.getStockId() != null) {
            Stock existStock = stockRepository.findById(stock.getStockId()).orElse(null);
            if (existStock != null) {
                existStock.setAvailableStock(stock.getAvailableStock());
                existStock.setFullStock(stock.getFullStock());
                existStock.setWarehouse(warehouseService.getWarehouseById(warehouseId));

            }
            return null;
        }
        Stock newStock = new Stock();
        newStock.setAvailableStock(stock.getAvailableStock());
        newStock.setFullStock(stock.getFullStock());
        newStock.setProduct(productService.getProductById(stock.getProductId()));
        newStock.setWarehouse(warehouseService.getWarehouseById(warehouseId));
        return toStockResponse(stockRepository.save(newStock));
    }

    public Stock updateStock(Long warehouseId, Long stockId, StockRequest stock) {
        Optional<Stock> existStock = stockRepository.findById(stockId);
        if (existStock.isPresent()) {
            if (warehouseId != null && !warehouseId.equals(existStock.get().getWarehouse().getId())) {
                return null;
            }
            existStock.get().setAvailableStock(stock.getAvailableStock());
            existStock.get().setFullStock(stock.getFullStock());
            existStock.get().setWarehouse(warehouseService.getWarehouseById(warehouseId));
            return stockRepository.save(existStock.get());
        }
        return null;
    }

    public List<Stock> saveStocks(List<Stock> stocks) {
        return stockRepository.saveAll(stocks);
    }

    public StockResponse toStockResponse(Stock stock) {
        return new StockResponse(
                stock.getId(),
                stock.getProduct().getId(),
                stock.getProduct().getTitle(),
                stock.getProduct().getImageUrl(),
                stock.getAvailableStock(),
                stock.getFullStock());
    }

}
