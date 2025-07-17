package com.valise.invoice_generator.repositroy;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.valise.invoice_generator.model.VerificationCode;

public interface VerificationRepository extends JpaRepository<VerificationCode, Long> {
    Optional<VerificationCode> findTopByEmailOrderByIdDesc(String email);
}

