package com.florry.user.signup.service;

import com.florry.user.signup.dto.SignUpRequest;
import com.florry.user.signup.dto.SignUpResponse;

public interface SignUpService {
    SignUpResponse signUp(SignUpRequest signUpRequest);
}
