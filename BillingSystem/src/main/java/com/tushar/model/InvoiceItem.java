package com.tushar.model;

public class InvoiceItem {
    private int itemId;
    private Product product;
    private int quantity;
    private double price; // price at purchase time


    public InvoiceItem() {
    }

    public InvoiceItem(int itemId, Product product, int quantity, double price) {
        this.itemId = itemId;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

