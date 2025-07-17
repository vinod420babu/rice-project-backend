package com.valise.invoice_generator.repositroy;


import com.valise.invoice_generator.model.OtpVerification;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OtpRepo extends JpaRepository<OtpVerification, Long> {
    OtpVerification findByEmailAndOtp(String email, String otp);
}
