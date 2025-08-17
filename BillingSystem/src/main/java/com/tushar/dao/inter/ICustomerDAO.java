package com.tushar.dao.inter;

import com.tushar.model.Customer;

import java.util.List;

public interface ICustomerDAO {
    void addCustomer(Customer customer);
    Customer getCustomerById(int id);
    List<Customer> getAllCustomers();
    void updateCustomer(Customer customer);
    void deleteCustomer(int id);
}
