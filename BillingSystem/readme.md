
```markdown
# Billing System using Spring Core & JDBC Template

## Overview
This is a simple **Billing System** built using **Spring Core** and **JDBC Template**.  
It allows managing **customers**, **products**, and **invoices** with line items.  
The application is designed as a **console-based program**, but the structure can be extended to a web application.

---

## Features
- Add, update, view, and delete **Customers**
- Add, update, view, and delete **Products**
- Create **Invoices** for customers with multiple products
- Automatically calculate **total amount** for invoices
- Update **product stock** after invoice generation
- View invoices with all purchased items

---
```

## Project Structure
```
com.tushar
├── model
│   ├── Customer.java
│   ├── Product.java
│   ├── Invoice.java
│   └── InvoiceItem.java
├── dao
│   ├── CustomerDAO.java / CustomerDAOImpl.java
│   ├── ProductDAO.java / ProductDAOImpl.java
│   ├── InvoiceDAO.java / InvoiceDAOImpl.java
│   └── InvoiceItemDAO.java / InvoiceItemDAOImpl.java
├── service
│   ├── ICustomerService.java / CustomerService.java
│   ├── IProductService.java / ProductService.java
│   ├── IInvoiceService.java / InvoiceService.java
│   └── IBillingService.java / BillingServiceImpl.java
├── MainApp.java
└── applicationContext.xml

````

---

## Database Schema

### Customers Table
```sql
CREATE TABLE customers (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    phone VARCHAR(15),
    email VARCHAR(100)
);
````

### Products Table

```sql
CREATE TABLE products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    price DECIMAL(10,2),
    stock INT
);
```

### Invoices Table

```sql
CREATE TABLE invoices (
    invoice_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    total_amount DECIMAL(10,2),
    invoice_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);
```

### Invoice Items Table

```sql
CREATE TABLE invoice_items (
    item_id INT AUTO_INCREMENT PRIMARY KEY,
    invoice_id INT,
    product_id INT,
    quantity INT,
    price DECIMAL(10,2),
    FOREIGN KEY (invoice_id) REFERENCES invoices(invoice_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);
```

---

## Technologies Used

* Java 8+
* Spring Core
* JDBC Template
* MySQL 
* Maven (for dependency management)

---

## How to Run

1. Clone the repository:

```bash
git clone <repository-url>
```

2. Import the project into your favorite IDE (Eclipse, IntelliJ, etc.)
3. Update `applicationContext.xml` with your database credentials
4. Make sure the database and tables exist
5. Run `MainApp.java`
6. Use the console menu to interact with the system

---

## Example Flow

1. Add a new customer
2. Add products with stock
3. Create an invoice for the customer
4. Select products and quantity
5. Invoice is stored, and product stock is updated
6. View invoice with items and total amount

---

## Author

* **Tushar Shitole**
*  Java Full Stack & MERN Stack Developer

---

```
