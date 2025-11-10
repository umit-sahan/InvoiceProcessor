package com.example.invoice_processor.service;

import com.example.invoice_processor.dto.InvoiceRequest;

public interface InvoiceService {
    void processInvoice(InvoiceRequest request);
}