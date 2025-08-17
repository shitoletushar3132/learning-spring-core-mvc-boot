package com.tushar.event;

import org.springframework.context.ApplicationListener;

public class InvoiceEventListener implements ApplicationListener<InvoiceCreatedEvent> {
    @Override
    public void onApplicationEvent(InvoiceCreatedEvent event) {
        System.out.println("Invoice created for customer: " + event.getInvoice().getCustomer().getName());
        // you can send email, SMS, or log here
    }
}
