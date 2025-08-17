package com.tushar.dao.inter;

import com.tushar.model.InvoiceItem;

import java.util.List;

public interface IInvoiceItemDAO {
    void addInvoiceItem(InvoiceItem item, int invoiceId);
    void deleteInvoiceItem(int invoiceId);
    List<InvoiceItem> getItemsByInvoiceId(int invoiceId);
}
