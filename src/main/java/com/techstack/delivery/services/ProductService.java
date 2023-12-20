package com.techstack.delivery.services;

import com.techstack.delivery.entities.Product;
import org.springframework.stereotype.Service;

public interface ProductService {
    Iterable<Product> listAllProducts();

    Product getProductById(Long id);

    Product saveProduct(Product product);

    void deleteProduct(Long id);
}
