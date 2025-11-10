package com.example.invoice_processor.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "invoices")
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nip", nullable = false, length = 20)
    private String nip;

    @Column(name = "p1")
    private LocalDate p1;

    @Column(name = "p2", length = 100)
    private String p2;

    @Column(name = "created_at")
    private LocalDate createdAt;


    public InvoiceEntity() {
        this.createdAt = LocalDate.now();
    }

    public InvoiceEntity(String nip, LocalDate p1, String p2) {
        this();
        this.nip = nip;
        this.p1 = p1;
        this.p2 = p2;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public LocalDate getP1() {
        return p1;
    }

    public void setP1(LocalDate p1) {
        this.p1 = p1;
    }

    public String getP2() {
        return p2;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}