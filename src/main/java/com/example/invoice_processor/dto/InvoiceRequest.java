package com.example.invoice_processor.dto;

import jakarta.validation.constraints.NotBlank;

public class InvoiceRequest {

    @NotBlank(message = "Base64 XML is required")
    private String base64xml;


    public InvoiceRequest() {}

    public InvoiceRequest(String base64xml) {
        this.base64xml = base64xml;
    }


    public String getBase64xml() {
        return base64xml;
    }

    public void setBase64xml(String base64xml) {
        this.base64xml = base64xml;
    }
}