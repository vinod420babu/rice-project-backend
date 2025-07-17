package com.valise.invoice_generator.controller;

import com.valise.invoice_generator.dto.SignupRequest;
import com.valise.invoice_generator.dto.UserDTO;
import com.valise.invoice_generator.model.User;
import com.valise.invoice_generator.service.UserService;
import com.valise.invoice_generator.repositroy.UserRepository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class SignupController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")    
    
    public String signup(@RequestBody User user) {
        return userService.registerUser(user);
    }
}
