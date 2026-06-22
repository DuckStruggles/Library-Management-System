package com.srishti.libManagement.Service;

import com.srishti.libManagement.DTO.RegisterRequestDTO;
import com.srishti.libManagement.Model.User;
import com.srishti.libManagement.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(RegisterRequestDTO request) {

        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // BCrypt later
        user.setRole("USER");

        userRepository.save(user);
    }
}