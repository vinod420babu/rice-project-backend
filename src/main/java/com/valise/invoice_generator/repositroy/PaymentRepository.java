package com.valise.invoice_generator.repositroy;

import com.valise.invoice_generator.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Optional<Payment> findByInvoiceNo(String invoiceNumber);
}
