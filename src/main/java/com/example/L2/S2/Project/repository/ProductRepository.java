package com.example.L2.S2.Project.repository;

import com.example.L2.S2.Project.dao.request.ProductRequest;
import com.example.L2.S2.Project.model.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT new com.example.L2.S2.Project.dao.request.ProductRequest(p.id, p.title,p.description,p.brand, c.id, p.price, p.imageUrl) FROM Product p JOIN p.category c")
    List<ProductRequest> findAllProductRequests();
}
