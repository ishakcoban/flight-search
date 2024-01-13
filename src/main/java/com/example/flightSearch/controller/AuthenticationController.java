package com.example.flightSearch.controller;

import com.example.flightSearch.modal.request.LoginRequest;
import com.example.flightSearch.modal.request.RegisterRequest;
import com.example.flightSearch.modal.response.AuthenticationResponse;
import com.example.flightSearch.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;

    @Operation(description = "Login to the system.")
    @PostMapping("/login")
    public AuthenticationResponse authenticate(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @Operation(description = "Register to the system.")
    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest request) {
        authService.register(request);
    }

}
