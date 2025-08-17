package com.tushar.service;

import com.tushar.dao.inter.IInvoiceDAO;
import com.tushar.dao.inter.IInvoiceItemDAO;
import com.tushar.exception.InvoiceServiceException;
import com.tushar.model.Invoice;
import com.tushar.model.InvoiceItem;
import com.tushar.service.inter.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class InvoiceService implements IInvoiceService {

    @Autowired
    private IInvoiceDAO invoiceDAO;

    @Autowired
    private IInvoiceItemDAO invoiceItemDAO;

    @Override
    @Transactional
    public void createInvoice(Invoice invoice) {
        try {
            int invoiceId = invoiceDAO.createInvoice(invoice);
            for (InvoiceItem item : invoice.getItems()) {
                invoiceItemDAO.addInvoiceItem(item, invoiceId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new InvoiceServiceException("Failed to create invoice", e);
        }
    }

    @Override
    @Transactional
    public Invoice getInvoiceById(int id) {
        try {
            Invoice invoice = invoiceDAO.getInvoiceById(id);
            if (invoice != null) {
                invoice.setItems(invoiceItemDAO.getItemsByInvoiceId(id));
            }
            return invoice;
        } catch (Exception e) {
            throw new InvoiceServiceException("Failed to fetch invoice with id: " + id, e);
        }
    }

    @Override
    @Transactional
    public List<Invoice> getAllInvoices() {
        try {
            List<Invoice> invoices = invoiceDAO.getAllInvoices();
            for (Invoice invoice : invoices) {
                invoice.setItems(invoiceItemDAO.getItemsByInvoiceId(invoice.getInvoiceId()));
            }
            return invoices;
        } catch (Exception e) {
            throw new InvoiceServiceException("Failed to fetch all invoices", e);
        }
    }

    @Override
    @Transactional
    public void deleteInvoice(int id) {
        try {
            invoiceItemDAO.deleteInvoiceItem(id);
            invoiceDAO.deleteInvoiceById(id);
        } catch (Exception e) {
            throw new InvoiceServiceException("Failed to delete invoice with id: " + id, e);
        }
    }
}
