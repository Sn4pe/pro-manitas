package com.promanitas.promanitas.controllers;

import com.promanitas.promanitas.security.jwt.payload.request.LoginRequest;
import com.promanitas.promanitas.security.jwt.payload.request.SignupRequest;
import com.promanitas.promanitas.security.jwt.payload.request.TokenRefreshRequest;
import com.promanitas.promanitas.security.jwt.payload.response.JwtResponse;
import com.promanitas.promanitas.security.jwt.payload.response.MessageResponse;
import com.promanitas.promanitas.security.jwt.payload.response.TokenRefreshResponse;
import com.promanitas.promanitas.services.interfaces.IAuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return authService.registerUser(signUpRequest);
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<TokenRefreshResponse> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        return authService.refreshtoken(request);
    }

    @PostMapping("/signout")
    public ResponseEntity<MessageResponse> logoutUser() {
        return authService.logoutUser();
    }
}
