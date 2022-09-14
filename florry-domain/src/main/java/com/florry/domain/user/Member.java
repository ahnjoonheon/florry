package com.florry.domain.user;

import com.florry.common.constant.MemberRole;
import com.florry.common.constant.MemberStatus;
import com.florry.domain.common.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Table(
        name = "MEMBER",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "NICKNAME", name = "UQ_USER_NICKNAME"),
                @UniqueConstraint(columnNames = "EMAIL", name = "UQ_USER_EMAIL")
        }
)
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "NAME")
    private String name;

    @Column(name = "NICKNAME", nullable = false)
    private String nickName;

    @Column(name = "SSN")
    private String ssn;

    @Column(name = "MOBILE", nullable = false)
    private String mobile;

    @Enumerated(EnumType.STRING)
    @Column(name = "MEMBER_ROLE", nullable = false)
    private MemberRole memberRole;

    @Enumerated(EnumType.STRING)
    @Column(name = "MEMBER_STATUS", nullable = false)
    private MemberStatus memberStatus;

    protected Member() {
    }

    public Member withRoleBySignUp(MemberRole memberRole) {
        this.memberRole = memberRole;
        return this;
    }

    public Member withUserStatusBySignUp(MemberStatus memberStatus) {
        this.memberStatus = memberStatus;
        return this;
    }

    public Member update(Member member) {
        this.email = member.email;
        this.password = member.password;
        this.name = member.name;
        this.nickName = member.nickName;
        this.ssn = member.ssn;
        this.mobile = member.mobile;
        this.memberRole = member.memberRole;
        this.memberStatus = member.memberStatus;
        return this;
    }

    public void withdraw() {
        this.memberStatus = MemberStatus.WITHDRAW;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getNickName() {
        return nickName;
    }

    public String getSsn() {
        return ssn;
    }

    public String getMobile() {
        return mobile;
    }

    public MemberRole getMemberRole() {
        return memberRole;
    }

    public MemberStatus getUserStatus() {
        return memberStatus;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static final class UserBuilder {
        private Long id;
        private String email;
        private String password;
        private String name;
        private String nickName;
        private String ssn;
        private String mobile;
        private MemberRole memberRole;
        private MemberStatus memberStatus;

        private UserBuilder() {
        }

        public static UserBuilder anUser() {
            return new UserBuilder();
        }

        public UserBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder nickName(String nickName) {
            this.nickName = nickName;
            return this;
        }

        public UserBuilder ssn(String ssn) {
            this.ssn = ssn;
            return this;
        }

        public UserBuilder mobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

        public UserBuilder userRole(MemberRole memberRole) {
            this.memberRole = memberRole;
            return this;
        }

        public UserBuilder userStatus(MemberStatus memberStatus) {
            this.memberStatus = memberStatus;
            return this;
        }

        public Member build() {
            Member member = new Member();
            member.mobile = this.mobile;
            member.email = this.email;
            member.name = this.name;
            member.memberStatus = this.memberStatus;
            member.nickName = this.nickName;
            member.password = this.password;
            member.memberRole = this.memberRole;
            member.ssn = this.ssn;
            member.id = this.id;
            return member;
        }
    }
}
