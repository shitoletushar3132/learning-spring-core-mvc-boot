package com.tushar.model;

import java.util.Date;
import java.util.List;

public class Invoice {
    private int invoiceId;
    private Customer customer;
    private List<InvoiceItem> items;
    private double totalAmount;
    private Date invoiceDate;

    public Invoice() {

    }

    public Invoice(double totalAmount, List<InvoiceItem> items, Customer customer, int invoiceId) {
        this.totalAmount = totalAmount;
        this.items = items;
        this.customer = customer;
        this.invoiceId = invoiceId;
    }

    // Getters and Setters
    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<InvoiceItem> getItems() {
        return items;
    }

    public void setItems(List<InvoiceItem> items) {
        this.items = items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
}
