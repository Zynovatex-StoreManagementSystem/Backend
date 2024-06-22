package com.example.L2.S2.Project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.L2.S2.Project.dao.request.ProductStockSummaryRequest;
import com.example.L2.S2.Project.dao.response.StockResponse;
import com.example.L2.S2.Project.model.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {

        @Query("SELECT new com.example.L2.S2.Project.dao.response.StockResponse(s.id, p.id, p.title, p.imageUrl, s.availableStock, s.fullStock) "
                        +
                        "FROM Stock s " +
                        "JOIN s.product p " +
                        "JOIN s.warehouse w " +
                        "WHERE w.id = :warehouseId")
        List<StockResponse> findStockByWarehouseId(Long warehouseId);

        List<Stock> findByProductId(Long productId);

        Stock findByProductIdAndWarehouseId(Long productId, Long warehouseId);

        Stock findByProductCategoryName(String categoryName);

        Stock findByProductCategoryNameAndWarehouseLocation(String categoryName, String location);

        @Query("SELECT new com.example.L2.S2.Project.dao.request.ProductStockSummaryRequest(s.product.id, s.product.title, s.product.category.name, s.product.imageUrl, SUM(s.fullStock), SUM(s.availableStock)) "
                        +
                        "FROM Stock s " +
                        "GROUP BY s.product.id, s.product.title, s.product.category.name, s.product.imageUrl")
        List<ProductStockSummaryRequest> findProductStockSummaries();

}