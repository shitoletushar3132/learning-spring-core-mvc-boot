package com.tushar.dao;

import com.tushar.dao.inter.IInvoiceItemDAO;
import com.tushar.model.InvoiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class InvoiceItemDAO implements IInvoiceItemDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void addInvoiceItem(InvoiceItem item, int invoiceId) {
        String sql = "INSERT INTO invoice_items (invoice_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, invoiceId, item.getProduct().getId(), item.getQuantity(), item.getPrice());
    }

    @Override
    public void deleteInvoiceItem(int invoiceId) {
        String sql = "DELETE FROM invoice_items WHERE invoice_id=?";
        jdbcTemplate.update(sql,invoiceId);

    }

    @Override
    public List<InvoiceItem> getItemsByInvoiceId(int invoiceId) {
        String sql = "SELECT id, product_id, quantity, price FROM invoice_items WHERE invoice_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(InvoiceItem.class), invoiceId);
    }
}
