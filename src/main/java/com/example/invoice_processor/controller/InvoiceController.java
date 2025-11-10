package com.example.invoice_processor.controller;

import com.example.invoice_processor.dto.ApiResponse;
import com.example.invoice_processor.dto.InvoiceRequest;
import com.example.invoice_processor.service.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createInvoice(@Valid @RequestBody InvoiceRequest request) {
        invoiceService.processInvoice(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse("Invoice saved successfully"));
    }
}