package com.florry.user.signup.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.florry.user.AbstractControllerTest;
import com.florry.user.signup.dto.SignUpRequest;
import com.florry.user.signup.dto.SignUpResponse;
import com.florry.user.signup.service.SignUpService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class SignUpRestControllerTest extends AbstractControllerTest {
    private static final String DEFAULT_API_URL = "/api/sign-up";

    @Autowired
    private SignUpRestController controller;

    @MockBean
    private SignUpService signUpService;

    @MockBean
    private SignUpRequest signUpRequest;

    @MockBean
    private SignUpResponse signUpResponse;

    @Override
    protected Object controller() {
        return this.controller;
    }

    @Test
    @DisplayName("사용자 가입 요청")
    void signUp() throws Exception {
        // given
        given(this.signUpService.signUp(any(SignUpRequest.class))).willReturn(this.signUpResponse);
        given(this.signUpResponse.getId()).willReturn(1L);

        // when - then
        mockMvc.perform(post(DEFAULT_API_URL)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(this.signUpRequest)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string(HttpHeaders.LOCATION, "/api/users/1"));
    }
}
