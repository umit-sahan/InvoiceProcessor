package com.example.invoice_processor.service.impl;

import com.example.invoice_processor.dto.InvoiceRequest;
import com.example.invoice_processor.entity.InvoiceEntity;
import com.example.invoice_processor.repository.InvoiceRepository;
import com.example.invoice_processor.service.InvoiceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final XmlProcessingService xmlProcessingService;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository,
                              XmlProcessingService xmlProcessingService) {
        this.invoiceRepository = invoiceRepository;
        this.xmlProcessingService = xmlProcessingService;
    }

    @Override
    public void processInvoice(InvoiceRequest request) {
        InvoiceEntity invoiceEntity = xmlProcessingService.processXml(request.getBase64xml());
        invoiceRepository.save(invoiceEntity);
    }
}