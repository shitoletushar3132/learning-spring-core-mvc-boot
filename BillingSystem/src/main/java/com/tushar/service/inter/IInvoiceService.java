package com.tushar.service.inter;

import com.tushar.model.Invoice;

import java.util.List;

public interface IInvoiceService {
    void createInvoice(Invoice invoice);
    Invoice getInvoiceById(int id);
    List<Invoice> getAllInvoices();
    void deleteInvoice(int id);
}
