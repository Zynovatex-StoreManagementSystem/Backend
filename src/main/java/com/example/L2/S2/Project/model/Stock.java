package com.example.L2.S2.Project.model;

import com.example.L2.S2.Project.dao.request.StockRequest;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Stocks")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int availableStock;
    private int fullStock;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    public Stock(int availableStock, int fullStock, Product product, Warehouse warehouse) {
        this.availableStock = availableStock;
        this.fullStock = fullStock;
        this.product = product;
        this.warehouse = warehouse;
    }

    public Stock(StockRequest stockRequest){
        this.availableStock = stockRequest.getAvailableStock();
        this.fullStock = stockRequest.getFullStock();
        this.id = stockRequest.getStockId();
    }
}