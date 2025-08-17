package com.tushar.dao.inter;

import com.tushar.model.Invoice;

import java.util.List;

public interface IInvoiceDAO {
    int createInvoice(Invoice invoice); // returns generated invoiceId
    Invoice getInvoiceById(int id);
    List<Invoice> getAllInvoices();
    void deleteInvoiceById(int id);
}
