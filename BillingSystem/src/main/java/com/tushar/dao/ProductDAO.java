package com.tushar.dao;

import com.tushar.dao.inter.IProductDAO;
import com.tushar.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

public class ProductDAO implements IProductDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addProduct(Product product) {
        try {
            String sql = "INSERT INTO products (id, name, price, stock) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql, product.getId(), product.getName(), product.getPrice(), product.getStock());
        } catch (DataAccessException e) {
            System.out.println("Error while adding product: " + e.getMessage());
            // optionally rethrow or log
        }
    }

    @Override
    public Product getProductById(int id) {
        try {
            String sql = "SELECT * FROM products WHERE id=?";
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Product.class), id);
        } catch (DataAccessException e) {
            System.out.println("Error while fetching product by ID: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Product> getAllProducts() {
        try {
            String sql = "SELECT * FROM products";
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));
        } catch (DataAccessException e) {
            System.out.println("Error while fetching all products: " + e.getMessage());
            return List.of();
        }
    }

    @Override
    public void updateProduct(Product product) {
        try {
            String sql = "UPDATE products SET name=?, price=?, stock=? WHERE id=?";
            jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getStock(), product.getId());
        } catch (DataAccessException e) {
            System.out.println("Error while updating product: " + e.getMessage());
        }
    }

    @Override
    public void deleteProduct(int id) {
        try {
            String sql = "DELETE FROM products WHERE id=?";
            jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            System.out.println("Error while deleting product: " + e.getMessage());
        }
    }

    @Override
    public void updateStock(int productId, int newStock) {
        try {
            String sql = "UPDATE products SET stock=? WHERE id=?";
            jdbcTemplate.update(sql, newStock, productId);
        } catch (DataAccessException e) {
            System.out.println("Error while updating stock: " + e.getMessage());
        }
    }
}
