package com.tushar.model;

public class Product {
    private String id;
    private String name;
    private double price;
    private int stock;

    public Product() {}

    public Product(String id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    // getters / setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public double getTotalPrice() { return price * stock; }

    @Override
    public String toString() {
        return String.format("%s (%s) x%d @ %.2f = %.2f",
                name, id, stock, price, getTotalPrice());
    }
}
