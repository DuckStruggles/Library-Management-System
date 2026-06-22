package com.srishti.libManagement.Controller;

import com.srishti.libManagement.DTO.RegisterRequestDTO;
import com.srishti.libManagement.Service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(
            @RequestBody RegisterRequestDTO request) {

        authService.register(request);

        return "User registered successfully";
    }
}