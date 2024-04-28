package com.example.L2.S2.Project.service;

import com.example.L2.S2.Project.model.Product;
import com.example.L2.S2.Project.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;


    public List<Product> allProduct() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    public Product updateProductById(Long id, Product product) {
        Optional<Product> existProduct = productRepository.findById(id);
        if (existProduct.isPresent()) {
            Product orginalProduct = existProduct.get();
            if (Objects.nonNull(product.getTitle()) && !"".equalsIgnoreCase(product.getTitle())) {
                orginalProduct.setTitle(product.getTitle());
            }
            if (product.getPrice() != 0) {
                orginalProduct.setPrice(product.getPrice());
            }
            if (product.getQuantity() != 0) {
                orginalProduct.setQuantity(product.getQuantity());
            }
            if (!"".equalsIgnoreCase(product.getImageUrl())) {
                orginalProduct.setImageUrl(product.getImageUrl());
            }
            if (!"".equalsIgnoreCase(product.getDescription())) {
                orginalProduct.setDescription(product.getDescription());
            }
            if (!"".equalsIgnoreCase(product.getBrand())) {
                orginalProduct.setBrand(product.getBrand());
            }
            return productRepository.save(orginalProduct);
        }
        return null;
    }
    public String deleteProductById(Long id) {
        if (productRepository.findById(id).isPresent()) {
            productRepository.deleteById(id);
            return "product deleted successfully...";
        }
        return "no such product in the database";
    }
}
