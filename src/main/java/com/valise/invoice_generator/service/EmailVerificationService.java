package com.valise.invoice_generator.service;

import java.time.LocalDateTime;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.valise.invoice_generator.model.VerificationCode;
import com.valise.invoice_generator.repositroy.VerificationRepository;

@Service
public class EmailVerificationService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private VerificationRepository verificationRepository;

    public void sendVerificationCode(String email) {
        String code = String.valueOf(new Random().nextInt(999999)); // 6-digit OTP

        VerificationCode verification = new VerificationCode(email, code, LocalDateTime.now().plusMinutes(5));
        verificationRepository.save(verification);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your Login Verification Code");
        message.setText("Your verification code is: " + code);

        mailSender.send(message);
    }
    
    public boolean verifyCode(String email, String code) {
        Optional<VerificationCode> optional = verificationRepository.findTopByEmailOrderByIdDesc(email);

        if (optional.isPresent()) {
            VerificationCode savedCode = optional.get();
            if (savedCode.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

}

