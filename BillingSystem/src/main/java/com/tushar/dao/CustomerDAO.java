package com.tushar.dao;

import com.tushar.dao.inter.ICustomerDAO;
import com.tushar.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CustomerDAO implements ICustomerDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addCustomer(Customer customer) {
        try {
            String sql = "INSERT INTO customers (name, phone) VALUES (?,?)";
            jdbcTemplate.update(sql, customer.getName(), customer.getPhone());
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to add customer: " + customer.getName(), e);
        }
    }

    @Override
    public Customer getCustomerById(int id) {
        try {
            String sql = "SELECT * FROM customers WHERE id=?";
            Customer customer = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Customer.class), id);
            return customer;
        } catch (DataAccessException e) {
            throw new RuntimeException("Customer not found with id: " + id, e);
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        try {
            String sql = "SELECT * FROM customers";
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Customer.class));
        } catch (DataAccessException e) {
            throw new RuntimeException("Error While Fetching the All Customers " + e);
        }
    }

    @Override
    public void updateCustomer(Customer customer) {
        try {
            String sql = "UPDATE customers SET name=?, phone=? WHERE id=?";
            jdbcTemplate.update(sql, customer.getName(), customer.getPhone(), customer.getId());
        } catch (DataAccessException e) {
            throw new RuntimeException("Error while updating customer with id: " + customer.getId(), e);
        }
    }

    @Override
    public void deleteCustomer(int id) {
        try{
            String sql = "DELETE FROM customers WHERE id=?";
            jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error While Deleting the Customer with Id: " + id, e);
        }

    }
}
