package com.florry.user.api.auth.ui;

import com.florry.user.api.auth.dto.LoginRequest;
import com.florry.user.api.auth.dto.LoginResponse;
import com.florry.user.api.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthRestController {

    private final AuthService authService;

    public AuthRestController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Validated LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}
