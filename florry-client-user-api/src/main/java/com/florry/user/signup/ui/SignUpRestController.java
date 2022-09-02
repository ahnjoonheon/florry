package com.florry.user.signup.ui;

import com.florry.user.signup.dto.SignUpRequest;
import com.florry.user.signup.dto.SignUpResponse;
import com.florry.user.signup.service.SignUpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/sign-up")
public class SignUpRestController {

    private final SignUpService signUpService;

    public SignUpRestController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @PostMapping
    public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.created(
                URI.create("/api/users/%d".formatted(signUpService.signUp(signUpRequest).id())))
                .build();
    }
}
