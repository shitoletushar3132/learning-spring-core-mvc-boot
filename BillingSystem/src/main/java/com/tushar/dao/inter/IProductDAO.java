package com.tushar.dao.inter;

import com.tushar.model.Product;

import java.util.List;

public interface IProductDAO {
    void addProduct(Product product);
    Product getProductById(int id);
    List<Product> getAllProducts();
    void updateProduct(Product product);
    void deleteProduct(int id);

    void updateStock(int productId, int newStock);
}
