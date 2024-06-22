package com.example.L2.S2.Project.controller;

import com.example.L2.S2.Project.dao.request.ProductRequest;
import com.example.L2.S2.Project.model.Product;
import com.example.L2.S2.Project.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductRequest> getAllProduct() {
        return productService.allProductBasics();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        return productService.deleteProductById(id);
    }

    @PostMapping
    public ProductRequest addProduct(@RequestBody ProductRequest product) {
        System.out.println(product);
        return productService.addProductRequest(product);
    }
}
