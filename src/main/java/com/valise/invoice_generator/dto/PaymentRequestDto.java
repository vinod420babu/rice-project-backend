package com.valise.invoice_generator.dto;

public class PaymentRequestDto {
	
	private String invoiceNo;
    private Double paidAmount;
    private Double totalAmount;
    private boolean fullPayment;
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public Double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public boolean isFullPayment() {
		return fullPayment;
	}
	public void setFullPayment(boolean fullPayment) {
		this.fullPayment = fullPayment;
	}
    
    
    

}
