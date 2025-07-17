package com.valise.invoice_generator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "gst_invoice_counters")
public class GstInvoiceCounter {
    
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

    private String gstNumber;

    private int lastInvoiceNumber;

	public String getGstNumber() {
		return gstNumber;
	}

	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}

	public int getLastInvoiceNumber() {
		return lastInvoiceNumber;
	}

	public void setLastInvoiceNumber(int lastInvoiceNumber) {
		this.lastInvoiceNumber = lastInvoiceNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
