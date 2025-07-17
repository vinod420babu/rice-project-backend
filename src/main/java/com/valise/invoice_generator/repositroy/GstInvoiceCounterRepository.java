package com.valise.invoice_generator.repositroy;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.valise.invoice_generator.model.GstInvoiceCounter;

public interface GstInvoiceCounterRepository extends JpaRepository<GstInvoiceCounter, Long> {
    Optional<GstInvoiceCounter> findByGstNumber(String gstNumber);

}
