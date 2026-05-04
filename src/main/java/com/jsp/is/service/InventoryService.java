package com.jsp.is.service;

import com.jsp.is.model.Product;
import com.jsp.is.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    @Autowired
    private ProductRepository productRepository;

    public boolean isInStock(String productName, int quantity) {
        Product product = productRepository.findByProductName(productName)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return product.getQuantity() >= quantity;
    }

    public void reduceStock(String productName, int quantity) {
        Product product = productRepository.findByProductName(productName)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getQuantity() < quantity) {
            throw new RuntimeException("Not enough stock");
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
    }
}