package com.valise.invoice_generator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valise.invoice_generator.model.GstInvoiceCounter;
import com.valise.invoice_generator.repositroy.GstInvoiceCounterRepository;

@Service
public class InvoiceNumberService {
	  @Autowired
	    private GstInvoiceCounterRepository counterRepository;

//	    	public String generateInvoiceNumber(String gstNumber) {
//
//	        // Clean GST number: remove all non-alphanumeric characters
//	        String gstKey = gstNumber.replaceAll("[^A-Z0-9]", "");
//	        String gstPrefix = gstKey.length() >= 5 ? gstKey.substring(gstKey.length() - 5) : gstKey;
//
//	        // Get last part of GST
//	        String[] parts = gstNumber.split("-");
//	        String lastElement = parts[parts.length - 1].trim();
//
//	        // Initialize counter
//	        int nextCount = 0;
//
//	        // Check for specific GST
//	        if (!lastElement.equalsIgnoreCase("35BOFPC2762F1ZA")) {
//	            return "Invalid GST suffix"; // or throw exception
//	        }
//
//	        // Fetch or initialize counter
//	        GstInvoiceCounter counter = counterRepository.findByGstNumber(gstNumber)
//	                .orElseGet(() -> {
//	                    GstInvoiceCounter newCounter = new GstInvoiceCounter();
//	                    newCounter.setGstNumber(gstNumber);
//	                    newCounter.setLastInvoiceNumber(0);
//	                    return newCounter;
//	                });
//
//	        int lastInvoice = counter.getLastInvoiceNumber();
//int nextInvoiceNum;
//	        // Determine next count
//	        if (lastInvoice == 0) {
//	        	nextInvoiceNum = 24;
//	        } else {
//	        	nextInvoiceNum =lastInvoice + 1;
//	        }
//
//	        // Increment and persist
//	        counter.setLastInvoiceNumber(nextInvoiceNum);
//	        counterRepository.save(counter);
//
//	        // Format invoice number
//	        String padded = String.format("%03d", nextInvoiceNum);
//
//	        System.out.println("Next invoice number: " + nextInvoiceNum);
//	        System.out.println("Formatted: " + padded);
//
//	        return "INV-" + gstPrefix + "-" + padded;
//	    
//}
	  public String generateInvoiceNumber(String gstNumber) {

		    // Clean GST number: remove all non-alphanumeric characters
		    String gstKey = gstNumber.replaceAll("[^A-Z0-9]", "");
		    String gstPrefix = gstKey.length() >= 5 ? gstKey.substring(gstKey.length() - 5) : gstKey;

		    // Use the full GST number as the key
		    GstInvoiceCounter counter = counterRepository.findByGstNumber(gstNumber)
		        .orElseGet(() -> {
		            GstInvoiceCounter newCounter = new GstInvoiceCounter();
		            newCounter.setGstNumber(gstNumber);
		            newCounter.setLastInvoiceNumber(0);
		            return newCounter;
		        });

		    int lastInvoice = counter.getLastInvoiceNumber();
		    int nextInvoiceNum;

		    if (lastInvoice == 0) {
		        // Check if it's the special GST that starts from 24
		        if (gstNumber.equalsIgnoreCase("20 A - M.A. Road, Phonix Bay, Port Blair, Andaman and Nicobar Islands - 744101 - 35BOFPC2762F1ZA")) {
		            nextInvoiceNum = 23;
		        } else {
		            nextInvoiceNum = 1; // Start from 001 for other GSTs
		        }
		    } else {
		        nextInvoiceNum = lastInvoice + 1;
		    }

		    counter.setLastInvoiceNumber(nextInvoiceNum);
		    counterRepository.save(counter);

		    String padded = String.format("%03d", nextInvoiceNum);

		    return "SAT" + "-" + 2025+ "-" + padded;
		    //return "SAT/" + 2024 + "/" + padded;
		}
}

