package com.valise.invoice_generator.controller;
import com.valise.invoice_generator.model.Payment;
import com.valise.invoice_generator.repositroy.PaymentRepository;
import com.valise.invoice_generator.service.PaymentService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/payment")
public class PaymentController {

	@Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentService paymentService;

    PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

//    // Save new invoice
    @PostMapping("/create")
    public ResponseEntity<Payment> createPayment(
            @RequestParam String invoiceNumber,
            @RequestParam double pendingAmount,
            @RequestParam double totalAmount) {
        Payment payment = paymentService.saveInitialPayment(invoiceNumber, totalAmount , pendingAmount);
        return ResponseEntity.ok(payment);
    }

    // Update invoice payment
//    @PutMapping("/update")
//    public ResponseEntity<Payment> updatePayment(
//            @RequestParam String invoiceNumber,
//            @RequestParam double paidAmount) {
//        Payment payment = paymentService.updatePayment(invoiceNumber, paidAmount);
//        return ResponseEntity.ok(payment);
//    }
    
    @PutMapping("/update")
    public ResponseEntity<Payment> updatePayment(
            @RequestParam String invoiceNo,
            @RequestParam(required = false) Double paidAmount,
            @RequestParam(defaultValue = "false") boolean fullPayment) {

//        Payment payment;
//
//        if (fullPayment) {
//            payment = paymentService.payFullAmount(invoiceNo);
//            System.out.println("Full payment processed for invoice: " + invoiceNo + " paid full amount: " + payment.getPaidAmount());
//        } else {
//            if (paidAmount == null) {
//                return ResponseEntity.badRequest().body(null);
//            }
//            payment = paymentService.updatePayment(invoiceNo, paidAmount);
//            System.out.println("Partial payment of ₹" + paidAmount + " processed for invoice: " + invoiceNo);
//        }
//
//
//        return ResponseEntity.ok(payment);
    	
        Optional<Payment> paymentOpt = paymentRepository.findByInvoiceNo(invoiceNo);

    	
    	if (paymentOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Payment payment = paymentOpt.get();
    	if (fullPayment) {
            double fullAmount = payment.getPendingAmount();
            payment.setPaidAmount(payment.getPaidAmount() + fullAmount);
            payment.setPendingAmount(0.0);
            payment.setStatus("Paid");
        } else {
            if (paidAmount == null) {
                return ResponseEntity.badRequest().body(null);
            }
            payment.setPaidAmount(payment.getPaidAmount() + paidAmount);
            payment.setPendingAmount(payment.getPendingAmount() - paidAmount);
            // update status accordingly
        }

        Payment updatedPayment = paymentRepository.save(payment);
        return ResponseEntity.ok(updatedPayment);
    }
    

    // Get invoice payment status
    // /get is not working i implemented the /all payment method
//    @GetMapping("/get")
//    public ResponseEntity<Payment> getPayment(
//            @RequestParam String invoiceNumber) {
//		System.out.println("working");
//        Payment payment = paymentService.getPayment(invoiceNumber);
//        return ResponseEntity.ok(payment);
//    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> allPayments = paymentRepository.findAll();
        return ResponseEntity.ok(allPayments);
    }
    @GetMapping("/search")
    public ResponseEntity<Payment> getPayment(@RequestParam String invoiceNo) {
        Payment payment = paymentService.getPayment(invoiceNo);
        return ResponseEntity.ok(payment);
    }

    @PutMapping("/mark-paid/{invoiceNo}")
    public ResponseEntity<?> markAsPaid(@PathVariable String invoiceNo) {
        paymentService.markAsPaid(invoiceNo);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/partial")
    public ResponseEntity<Payment> updatePartialPayment(
            @RequestParam String invoiceNo,
            @RequestParam double partialAmount) {
        
        Payment updatedPayment = paymentService.updatePartialPayment(invoiceNo, partialAmount);
        return ResponseEntity.ok(updatedPayment);
    }
}

