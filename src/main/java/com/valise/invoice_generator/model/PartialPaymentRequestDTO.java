package com.valise.invoice_generator.model;

public class PartialPaymentRequestDTO {
    private String invoiceNo;
    private double partialAmount;

    // Getters & Setters
    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public double getPartialAmount() {
        return partialAmount;
    }

    public void setPartialAmount(double partialAmount) {
        this.partialAmount = partialAmount;
    }
}
