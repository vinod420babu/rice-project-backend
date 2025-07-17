package com.valise.invoice_generator.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.valise.invoice_generator.dto.VerifyRequest;
import com.valise.invoice_generator.model.User;
import com.valise.invoice_generator.repositroy.UserRepository;
import com.valise.invoice_generator.service.EmailVerificationService;
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private EmailVerificationService emailVerificationService;
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/login/request")
    public ResponseEntity<Map<String, String>> requestLogin(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        emailVerificationService.sendVerificationCode(email);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Verification code sent to email");
        return ResponseEntity.ok(response);

    }

   
    @PostMapping("/login/verify")
    public ResponseEntity<String> verifyCode(@RequestBody VerifyRequest request) {
        String email = request.getEmail();
        String code = request.getCode();

        boolean isVerified = emailVerificationService.verifyCode(email, code);
        if (isVerified) {
            return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired code");
        }
    }
    
}


