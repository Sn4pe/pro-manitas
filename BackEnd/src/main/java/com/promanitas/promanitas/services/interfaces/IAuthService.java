package com.promanitas.promanitas.services.interfaces;

import com.promanitas.promanitas.security.jwt.payload.request.LoginRequest;
import com.promanitas.promanitas.security.jwt.payload.request.SignupRequest;
import com.promanitas.promanitas.security.jwt.payload.request.TokenRefreshRequest;
import com.promanitas.promanitas.security.jwt.payload.response.JwtResponse;
import com.promanitas.promanitas.security.jwt.payload.response.MessageResponse;
import com.promanitas.promanitas.security.jwt.payload.response.TokenRefreshResponse;
import org.springframework.http.ResponseEntity;

public interface IAuthService {
    ResponseEntity<JwtResponse> authenticateUser(LoginRequest loginRequest);
    ResponseEntity<MessageResponse> registerUser(SignupRequest signUpRequest);
    ResponseEntity<TokenRefreshResponse> refreshtoken(TokenRefreshRequest request);
    ResponseEntity<MessageResponse> logoutUser();
}
