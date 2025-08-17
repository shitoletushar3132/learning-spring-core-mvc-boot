package com.tushar.service.inter;

import com.tushar.model.Customer;

import java.util.List;

public interface ICustomerService {
    void addCustomer(Customer customer);
    Customer getCustomerById(int id);
    List<Customer> getAllCustomers();
    void updateCustomer(Customer customer);
    void deleteCustomer(int id);
}
