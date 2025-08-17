package com.tushar;

import com.tushar.exception.CustomerServiceException;
import com.tushar.exception.ProductServiceException;
import com.tushar.exception.InvoiceServiceException;
import com.tushar.model.Customer;
import com.tushar.model.InvoiceItem;
import com.tushar.model.Product;
import com.tushar.model.Invoice;
import com.tushar.service.inter.IProductService;
import com.tushar.service.inter.ICustomerService;
import com.tushar.service.inter.IInvoiceService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ConsoleApp {

    private final IProductService productService;
    private final ICustomerService customerService;
    private final IInvoiceService invoiceService;
    private final Scanner sc;

    public ConsoleApp(IProductService productService,
                      ICustomerService customerService,
                      IInvoiceService invoiceService) {
        this.productService = productService;
        this.customerService = customerService;
        this.invoiceService = invoiceService;
        this.sc = new Scanner(System.in);
    }

    public void showMenu() {
        boolean exit = false;

        while (!exit) {
            try {
                System.out.println("\n====== Billing System Menu ======");
                System.out.println("1. Add Product");
                System.out.println("2. Add Customer");
                System.out.println("3. Generate Bill");
                System.out.println("4. List Products");
                System.out.println("5. List Customers");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");

                int choice = Integer.parseInt(sc.nextLine().trim()); // safer than nextInt()

                switch (choice) {
                    case 1 -> addProduct();
                    case 2 -> addCustomer();
                    case 3 -> generateBill();
                    case 4 -> listProducts();
                    case 5 -> listCustomers();
                    case 6 -> {
                        System.out.println("Exiting...");
                        exit = true;
                    }
                    default -> System.out.println("Invalid choice! Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input! Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("❌ Unexpected error: " + e.getMessage());
            }
        }
    }

    private void addProduct() {
        try {
            System.out.print("Product ID: ");
            String id = sc.nextLine().trim();

            System.out.print("Product Name: ");
            String name = sc.nextLine().trim();

            System.out.print("Price: ");
            double price = Double.parseDouble(sc.nextLine().trim());

            System.out.print("Stock Size: ");
            int stock = Integer.parseInt(sc.nextLine().trim());

            Product product = new Product(id, name, price, stock);
            productService.addProduct(product);
            System.out.println("✅ Product added successfully!");
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid number format! Please enter numeric values for price and stock.");
        } catch (ProductServiceException e) {
            System.out.println("❌ Service error while adding product: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Unexpected error while adding product.");
        }
    }

    private void addCustomer() {
        try {
            System.out.print("Customer ID: ");
            int id = sc.nextInt();

            System.out.print("Customer Name: ");
            String name = sc.nextLine().trim();

            System.out.print("Phone: ");
            String phone = sc.nextLine().trim();

            Customer customer = new Customer(id, name, phone);
            customerService.addCustomer(customer);
            System.out.println("✅ Customer added successfully!");
        } catch (CustomerServiceException e) {
            System.out.println("❌ Service error while adding customer: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Unexpected error while adding customer.");
        }
    }

    private void generateBill() {
        try {
            System.out.print("Customer ID for billing: ");
            int customerId = Integer.parseInt(sc.nextLine().trim());
            Customer customer = customerService.getCustomerById(customerId);

            if (customer == null) {
                System.out.println("❌ Customer not found!");
                return;
            }

            System.out.print("Number of products to buy: ");
            int p = Integer.parseInt(sc.nextLine().trim());

            List<InvoiceItem> invoiceItems = new ArrayList<>();
            for (int i = 0; i < p; i++) {
                System.out.print("Enter product ID: ");
                int productId = Integer.parseInt(sc.nextLine().trim());
                Product product = productService.getProductById(productId);

                if (product != null) {
                    System.out.print("Enter Quantity of Product: ");
                    int quantity = Integer.parseInt(sc.nextLine().trim());
                    invoiceItems.add(new InvoiceItem(0, product, quantity, product.getPrice()));
                } else {
                    System.out.println("⚠️ Product not found: " + productId);
                }
            }

            if (invoiceItems.isEmpty()) {
                System.out.println("❌ No valid products selected. Cannot generate bill.");
                return;
            }

            // ✅ Fix: price * quantity
            double totalAmount = invoiceItems.stream()
                    .mapToDouble(item -> item.getPrice() * item.getQuantity())
                    .sum();

//            double totalAmount, List<InvoiceItem> items, Customer customer, int invoiceId
            // ✅ Correct constructor usage (adjust as per your Invoice class)
            Invoice invoice = new Invoice(totalAmount, invoiceItems, customer, 0);
            invoice.setInvoiceDate(new Date());
            invoiceService.createInvoice(invoice);

            System.out.println("✅ Bill generated successfully! Total Amount: " + totalAmount);

        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid number format! Please enter valid numbers.");
        } catch (InvoiceServiceException e) {
            System.out.println("❌ Service error while generating bill: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Unexpected error while generating bill.");
        }
    }


    private void listProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            if (products.isEmpty()) {
                System.out.println("⚠️ No products available.");
                return;
            }
            System.out.println("📦 All Products:");
            for (Product p : products) {
                System.out.println(p.getId() + " - " + p.getName() + " : " + p.getPrice()+" - "+p.getStock());
            }
        } catch (ProductServiceException e) {
            System.out.println("❌ Service error while listing products: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Unexpected error while listing products.");
        }
    }

    private void listCustomers() {
        try {
            List<Customer> customers = customerService.getAllCustomers();
            if (customers.isEmpty()) {
                System.out.println("⚠️ No customers available.");
                return;
            }
            System.out.println("👥 All Customers:");
            for (Customer c : customers) {
                System.out.println(c.getId() + " - " + c.getName() + " : " + c.getPhone());
            }
        } catch (CustomerServiceException e) {
            System.out.println("❌ Service error while listing customers: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Unexpected error while listing customers.");
        }
    }
}
