package com.valise.invoice_generator.service;
import com.valise.invoice_generator.dto.UserDTO;
import com.valise.invoice_generator.model.User;

public interface UserService {
    void registerUser(UserDTO userDTO);

	String registerUser(User user);
}
