package com.florry.domain.user;

import com.florry.common.constant.MemberRole;
import com.florry.common.constant.MemberStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("db")
@DisplayName("UserRepository 테스트 케이스")
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("User 저장")
    void save_user() {
        // given
        Long id = memberRepository.save(
                Member.builder().email("universal304015@gmail.com").password("1234").name("안준헌").nickName("얼도")
                        .mobile("01073508649").ssn("9201121459915").userRole(MemberRole.USER).userStatus(MemberStatus.NORMAL)
                        .build())
                .getId();

        // when
        final Member member = memberRepository.findById(id).orElseThrow();

        // then
        assertThat(member).isNotNull();
    }

    @Test
    @DisplayName("User 조회(검색조건: E-Mail, Password, UserStatus)")
    void find_user_by_email_password_user_status() {
        // given
        String email = "dkswnsgjs@naver.com";
        String password = "1234";
        MemberStatus memberStatus = MemberStatus.NORMAL;

        List<Member> members = List.of(
                Member.builder().email("universal304015@gmail.com").password("1234").name("안준헌").nickName("얼도")
                        .mobile("01073508649").ssn("9201121459915").userRole(MemberRole.USER).userStatus(MemberStatus.NORMAL)
                        .build(),
                Member.builder().email(email).password(password).name("안준헌").nickName("얼디")
                        .mobile("01073508649").ssn("9201121459916").userRole(MemberRole.USER).userStatus(memberStatus)
                        .build(),
                Member.builder().email("personal_dwg@naver.com").password("1234").name("안준헌").nickName("얼종")
                        .mobile("01073508649").ssn("9201121459917").userRole(MemberRole.USER).userStatus(MemberStatus.DORMANT)
                        .build());

        memberRepository.saveAll(members);

        // when
        final Member member = memberRepository.findByEmailAndPasswordAndMemberStatusIn(email, password, List.of(memberStatus)).orElseThrow();

        // then
        assertThat(member).isNotNull();
    }
}
