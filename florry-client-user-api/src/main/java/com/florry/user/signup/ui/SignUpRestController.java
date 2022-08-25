package com.florry.user.signup.ui;

import com.florry.domain.user.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sign-up")
public class SignUpRestController {

    private final UserRepository userRepository;

    public SignUpRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
