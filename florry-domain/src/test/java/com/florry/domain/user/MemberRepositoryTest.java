package com.florry.domain.user;

import com.florry.common.constant.MemberRole;
import com.florry.common.constant.MemberStatus;
import com.querydsl.core.BooleanBuilder;
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
    @DisplayName("Member 저장")
    void save_member() {
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
    @DisplayName("Member 다수 조회")
    void find_members() {
        // given
        String name = "안준헌";
        MemberStatus memberStatus = MemberStatus.NORMAL;


        List<Member> members = List.of(
                Member.builder().email("universal304015@gmail.com").password("1234").name(name).nickName("얼도")
                        .mobile("01073508649").ssn("9201121459915").userRole(MemberRole.USER).userStatus(memberStatus)
                        .build(),
                Member.builder().email("dkswnsgjs@naver.com").password("1234").name(name).nickName("얼디")
                        .mobile("01073508649").ssn("9201121459916").userRole(MemberRole.USER).userStatus(MemberStatus.NORMAL)
                        .build(),
                Member.builder().email("personal_dwg@naver.com").password("1234").name(name).nickName("얼종")
                        .mobile("01073508649").ssn("9201121459917").userRole(MemberRole.USER).userStatus(MemberStatus.DORMANT)
                        .build());

        memberRepository.saveAll(members);
        QMember qMember = QMember.member;

        // when
        Iterable<Member> memberIterable = memberRepository.findAll(new BooleanBuilder(qMember.name.eq(name)).and(qMember.memberStatus.eq(memberStatus)));

        // then
        assertThat(memberIterable).isNotNull();
    }

    @Test
    @DisplayName("Member 수정")
    void update_member() {
        // given
        Member member = memberRepository.save(Member.builder()
                .email("universal304015@gmail.com").password("1234").name("안준헌")
                .nickName("얼도").mobile("01073508649").ssn("9201121459915")
                .userRole(MemberRole.USER).userStatus(MemberStatus.NORMAL)
                .build());


        // when
        String updatedNickname = "얼탱";
        member.update(Member.builder()
                .email("universal304015@gmail.com").password("1234").name("안준헌")
                .nickName(updatedNickname).mobile("01073508649").ssn("9201121459915")
                .userRole(MemberRole.USER).userStatus(MemberStatus.NORMAL)
                .build());

        // then
        Member resultMember = memberRepository.findById(member.getId()).orElseThrow();
        assertThat(resultMember.getNickName()).isEqualTo(updatedNickname);
    }

    @Test
    @DisplayName("Member 탈퇴")
    void withdraw_member() {
        // given
        Member member = memberRepository.save(Member.builder()
                .email("universal304015@gmail.com").password("1234").name("안준헌")
                .nickName("얼도").mobile("01073508649").ssn("9201121459915")
                .userRole(MemberRole.USER).userStatus(MemberStatus.NORMAL)
                .build());
        // when
        member.withdraw();

        // then
        Member resultMember = memberRepository.findById(member.getId()).orElseThrow();
        assertThat(resultMember.getUserStatus()).isEqualTo(MemberStatus.WITHDRAW);
    }

    @Test
    @DisplayName("Member 상세 조회")
    void find_member_by_id() {
        // given
        Member member = memberRepository.save(Member.builder()
                .email("universal304015@gmail.com").password("1234").name("안준헌")
                .nickName("얼도").mobile("01073508649").ssn("9201121459915")
                .userRole(MemberRole.USER).userStatus(MemberStatus.NORMAL)
                .build());
        // when
        Member resultMember = memberRepository.findById(member.getId()).orElseThrow();

        // then
        assertThat(resultMember.getEmail()).isEqualTo("universal304015@gmail.com");
    }
}
