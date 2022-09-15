package com.florry.user.member.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.florry.user.AbstractControllerTest;
import com.florry.user.member.dto.*;
import com.florry.user.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@WebMvcTest(controllers = MemberRestController.class)
@DisplayName("MemberRestController 테스트")
class MemberRestControllerTest extends AbstractControllerTest {
    private static final String DEFAULT_API_URL = "/api/members";

    @Autowired
    private MemberRestController controller;

    @MockBean
    private MemberService memberService;

    @MockBean
    private SignUpRequest signUpRequest;

    @MockBean
    private SignUpResponse signUpResponse;

    @MockBean
    private MemberResponse memberResponse;

    @MockBean
    private MemberRequest memberRequest;

    @Override
    protected Object controller() {
        return this.controller;
    }

    @Test
    @DisplayName("회원 가입 요청")
    void signUp() throws Exception {
        // given
        given(this.memberService.signUp(any(SignUpRequest.class))).willReturn(this.signUpResponse);
        Long id = 1L;
        given(this.signUpResponse.id()).willReturn(id);

        // when - then
        mockMvc.perform(post(DEFAULT_API_URL)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(this.signUpRequest)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string(HttpHeaders.LOCATION, "/api/users/%d".formatted(id)));
    }

    @Test
    @DisplayName("회원 조회 요청")
    void findMembers() throws Exception {
        // given
        given(this.memberService.findMembersBySearchCondition(any(MemberSearchCondition.class))).willReturn(List.of(memberResponse));

        // when - then
        mockMvc.perform(get(DEFAULT_API_URL)
                        .param("name", "안준헌")
                        .param("nickName", "얼음포도"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원 수정 요청")
    void updateMember() throws Exception {
        // given
        given(memberService.updateMember(anyLong(), any(MemberRequest.class))).willReturn(memberResponse);

        // when - then
        mockMvc.perform(put(DEFAULT_API_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(new ObjectMapper().writeValueAsString(memberRequest)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원 탈퇴 요청")
    void withdrawMember() throws Exception {
        // given

        // when - then
        mockMvc.perform(delete(DEFAULT_API_URL + "/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("회원 상세 조회 요청")
    void findMember() throws Exception {
        // given
        given(this.memberService.findMember(anyLong())).willReturn(memberResponse);
        // when - then
        mockMvc.perform(get(DEFAULT_API_URL+"/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
