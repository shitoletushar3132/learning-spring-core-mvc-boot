package com.tushar.service.inter;

import com.tushar.model.Product;

import java.util.List;

public interface IProductService {
    void addProduct(Product product);

    Product getProductById(int id);

    List<Product> getAllProducts();

    void updateProduct(Product product);

    void deleteProduct(int id);
}
