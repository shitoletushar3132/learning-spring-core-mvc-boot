package com.tushar.dao;

import com.tushar.dao.inter.IInvoiceDAO;
import com.tushar.model.Invoice;
import com.tushar.model.InvoiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

public class InvoiceDAO implements IInvoiceDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public int createInvoice(Invoice invoice) {
        try {
            // 1. Insert Invoice & get generated ID
            String sql = "INSERT INTO invoices (customer_id, total_amount, invoice_date) VALUES (?, ?, ?)";

            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, invoice.getCustomer().getId());
                ps.setDouble(2, invoice.getTotalAmount());
                ps.setTimestamp(3, new Timestamp(invoice.getInvoiceDate().getTime()));
                return ps;
            }, keyHolder);

            Integer invoiceId = keyHolder.getKey().intValue();

            // 2. Insert Invoice Items
            String itemSql = "INSERT INTO invoice_items (invoice_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
            for (InvoiceItem item : invoice.getItems()) {
                jdbcTemplate.update(itemSql,
                        invoiceId,
                        item.getProduct().getId(),
                        item.getQuantity(),
                        item.getPrice()
                );
            }

            return invoiceId;

        } catch (DataAccessException e) {
            System.out.println("Error creating invoice: " + e);
            e.printStackTrace();
            return 0;
        }
    }


    @Override
    public Invoice getInvoiceById(int id) {
        try {
            // Fetch invoice
            String sql = "SELECT * FROM invoices WHERE invoice_id=?";
            Invoice invoice = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Invoice.class), id);

            // Fetch items
            String itemSql = "SELECT * FROM invoice_items WHERE invoice_id=?";
            List<InvoiceItem> items = jdbcTemplate.query(itemSql, new BeanPropertyRowMapper<>(InvoiceItem.class), id);

            invoice.setItems(items);
            return invoice;
        } catch (DataAccessException e) {
            System.out.println("Error fetching invoice: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Invoice> getAllInvoices() {
        try {
            String sql = "SELECT * FROM invoices";
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Invoice.class));
        } catch (DataAccessException e) {
            System.out.println("Error fetching invoices: " + e.getMessage());
            return List.of();
        }
    }

    @Override
    @Transactional
    public void deleteInvoiceById(int id) {
        try {
            // 1. Delete items first
            String sqlDeleteItems = "DELETE FROM invoice_items WHERE invoice_id = ?";
            jdbcTemplate.update(sqlDeleteItems, id);

            // 2. Delete invoice
            String sqlDeleteInvoice = "DELETE FROM invoices WHERE invoice_id = ?";
            jdbcTemplate.update(sqlDeleteInvoice, id);

            System.out.println("Invoice and its items deleted successfully for invoice_id=" + id);
        } catch (DataAccessException e) {
            System.out.println("Error while deleting invoice: " + e.getMessage());
            throw e; // rethrow so @Transactional rolls back
        }
    }
}
