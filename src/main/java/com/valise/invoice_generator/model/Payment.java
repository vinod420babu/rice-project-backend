package com.valise.invoice_generator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="payment")
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String invoiceNo;
    private double pendingAmount;
    private double totalAmount;
    private double paidAmount = totalAmount-pendingAmount;
    
    public Payment(String invoiceNumber2, double totalAmount) {
		// TODO Auto-generated constructor stub
    	this.invoiceNo=invoiceNumber2;
        this.totalAmount=paidAmount+pendingAmount;
        this.pendingAmount=totalAmount;
	}

	public Payment() {
		// TODO Auto-generated constructor stub
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setInvoiceNo(String invoiceNumber) {
		this.invoiceNo = invoiceNumber;
	}

	public double getPendingAmount() {
		return pendingAmount;
	}

	public void setPendingAmount(double pendingAmount) {
		this.pendingAmount = pendingAmount;
	}

	public double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public void setStatus(String string) {
		// TODO Auto-generated method stub
		
	}

//	public Payment() {
//        this.invoiceNumber=invoiceNumber;
//        this.pendingAmount=pendingAmount;
//    }
}
