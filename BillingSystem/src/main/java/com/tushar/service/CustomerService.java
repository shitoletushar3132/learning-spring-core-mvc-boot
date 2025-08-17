package com.tushar.service;

import com.tushar.dao.inter.ICustomerDAO;
import com.tushar.exception.CustomerServiceException;
import com.tushar.model.Customer;
import com.tushar.service.inter.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomerService implements ICustomerService {

    @Autowired
    private ICustomerDAO customerDAO;

    @Override
    public void addCustomer(Customer customer) {
        try {
            customerDAO.addCustomer(customer);
        } catch (Exception e) {
            throw new CustomerServiceException("Failed to add customer", e);
        }
    }

    @Override
    public Customer getCustomerById(int id) {
        try {
            return customerDAO.getCustomerById(id);
        } catch (Exception e) {
            throw new CustomerServiceException("Failed to fetch customer with ID: " + id, e);
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        try {
            return customerDAO.getAllCustomers();
        } catch (Exception e) {
            throw new CustomerServiceException("Failed to fetch all customers", e);
        }
    }

    @Override
    public void updateCustomer(Customer customer) {
        try {
            customerDAO.updateCustomer(customer);
        } catch (Exception e) {
            throw new CustomerServiceException("Failed to update customer", e);
        }
    }

    @Override
    public void deleteCustomer(int id) {
        try {
            customerDAO.deleteCustomer(id);
        } catch (Exception e) {
            throw new CustomerServiceException("Failed to delete customer with ID: " + id, e);
        }
    }
}
