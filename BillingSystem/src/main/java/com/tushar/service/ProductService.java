package com.tushar.service;

import com.tushar.dao.inter.IProductDAO;
import com.tushar.exception.ProductServiceException;
import com.tushar.model.Product;
import com.tushar.service.inter.IProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductService implements IProductService {

    @Autowired
    private IProductDAO productDAO;

    @Override
    public void addProduct(Product product) {
        try {
            productDAO.addProduct(product);
        } catch (Exception e) {
            throw new ProductServiceException("Failed to add product", e);
        }
    }

    @Override
    public Product getProductById(int id) {
        try {
            return productDAO.getProductById(id);
        } catch (Exception e) {
            throw new ProductServiceException("Failed to fetch product with ID: " + id, e);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        try {
            return productDAO.getAllProducts();
        } catch (Exception e) {
            throw new ProductServiceException("Failed to fetch all products", e);
        }
    }

    @Override
    public void updateProduct(Product product) {
        try {
            productDAO.updateProduct(product);
        } catch (Exception e) {
            throw new ProductServiceException("Failed to update product", e);
        }
    }

    @Override
    public void deleteProduct(int id) {
        try {
            productDAO.deleteProduct(id);
        } catch (Exception e) {
            throw new ProductServiceException("Failed to delete product with ID: " + id, e);
        }
    }
}
