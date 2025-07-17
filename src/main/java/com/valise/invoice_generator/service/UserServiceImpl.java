package com.valise.invoice_generator.service;
import com.valise.invoice_generator.dto.UserDTO;
import com.valise.invoice_generator.model.User;
import com.valise.invoice_generator.repositroy.UserRepository;
import com.valise.invoice_generator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
//    public void registerUser(UserDTO userDTO) {
//        if (userRepository.existsByEmail(userDTO.getEmail()) || userRepository.existsByMobile(userDTO.getMobile())) {
//            throw new IllegalArgumentException("User already exists with email or mobile.");
//        }
    
    public String registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return "Email already exists!";
        }

        User user1 = new User();
        user1.setEmail(user.getEmail());
        user1.setMobile(user.getMobile());
        user1.setPassword(user.getPassword()); // Consider hashing in real apps

        userRepository.save(user1);
		return null;
    }

	@Override
	public void registerUser(UserDTO userDTO) {
		// TODO Auto-generated method stub
		
	}
}
