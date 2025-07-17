package com.valise.invoice_generator.service;

import com.valise.invoice_generator.model.Payment;
import com.valise.invoice_generator.repositroy.PaymentRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment saveInitialPayment(String invoiceNumber, double pendingAmount,double totalAmount) {
        Payment payment = new Payment();
        payment.setInvoiceNo(invoiceNumber);
        payment.setPaidAmount(totalAmount-pendingAmount);
        payment.setPendingAmount(pendingAmount);
        payment.setTotalAmount(totalAmount);
        System.out.println("total amount"+pendingAmount);

        return paymentRepository.save(payment);

    }

    public Payment updatePayment(String invoiceNo, double paidAmount) {
    	System.out.println("calling update payment method");
        Payment payment = paymentRepository.findByInvoiceNo(invoiceNo)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        if (paidAmount > payment.getPendingAmount()) {
            throw new IllegalArgumentException("Paid amount exceeds pending amount");
        }

        payment.setPaidAmount(payment.getPaidAmount() + paidAmount);
        payment.setPendingAmount(payment.getPendingAmount() - paidAmount);
        return paymentRepository.save(payment);
    }

    public Payment getAllPayments(String invoiceNumber) {
        return paymentRepository.findByInvoiceNo(invoiceNumber)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
    }
    public void markAsPaid(String invoiceNo) {
        Payment payment = paymentRepository.findByInvoiceNo(invoiceNo)
            .orElseThrow(() -> new RuntimeException("Invoice not found"));

        payment.setPaidAmount(payment.getPaidAmount() + payment.getPendingAmount());
        payment.setPendingAmount(0);
        paymentRepository.save(payment);
    }
    
    public Payment updatePartialPayment(String invoiceNo, double partialAmount) {
        System.out.println("calling partial payment method for invoiceNo = " + invoiceNo);

        Payment payment = paymentRepository.findByInvoiceNo(invoiceNo)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        if (partialAmount <= 0) {
            throw new IllegalArgumentException("Partial amount must be greater than zero");
        }

        if (partialAmount > payment.getPendingAmount()) {
            throw new IllegalArgumentException("Partial amount exceeds pending amount");
        }

        payment.setPaidAmount(payment.getPaidAmount() + partialAmount);
        payment.setPendingAmount(payment.getPendingAmount() - partialAmount);

        // Optionally update status (if you have a status field)
        // if (payment.getPendingAmount() == 0) {
        //     payment.setStatus("Paid");
        // } else {
        //     payment.setStatus("Partial");
        // }

        return paymentRepository.save(payment);
    }
    
    public Payment payFullAmount(String invoiceNo) {
    	System.out.println("calling full payment method");
        System.out.println("calling full payment method with invoiceNo = " + invoiceNo);

//        Payment payment = paymentRepository.findByInvoiceNo(invoiceNo)
//                .orElseThrow(() -> new RuntimeException("Invoice not found"));
        Optional<Payment> optionalPayment = paymentRepository.findByInvoiceNo(invoiceNo);

        if (optionalPayment.isEmpty()) {
            System.out.println("Invoice not found for invoiceNo = " + invoiceNo);
            throw new RuntimeException("Invoice not found");
        }
        Payment payment = optionalPayment.get();

        double fullAmount = payment.getPendingAmount();
        payment.setPaidAmount(payment.getPaidAmount() + fullAmount);
        payment.setPendingAmount(0.0);
        payment.setStatus("Paid");
        System.out.println("calling full payment checking "+ fullAmount +" payment type"+payment);
        return paymentRepository.save(payment);
    }
    public Payment getPayment(String invoiceNo) {
        return paymentRepository.findByInvoiceNo(invoiceNo)
                .orElseThrow(() -> new RuntimeException("Invoice not found for invoiceNo: " + invoiceNo));
    }

}
