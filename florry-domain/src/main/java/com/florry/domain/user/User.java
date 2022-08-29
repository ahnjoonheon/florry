package com.florry.domain.user;

import com.florry.common.constant.UserRole;
import com.florry.common.constant.UserStatus;
import com.florry.domain.common.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Table(
        name = "USER",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "NICKNAME", name = "UQ_USER_NICKNAME"),
                @UniqueConstraint(columnNames = "EMAIL", name = "UQ_USER_EMAIL")
        }
)
public class User extends BaseTimeEntity {
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
    @Column(name = "ROLE", nullable = false)
    private UserRole userRole;

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_STATUS", nullable = false)
    private UserStatus userStatus;

    protected User() {
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

    public UserRole getUserRole() {
        return userRole;
    }

    public UserStatus getUserStatus() {
        return userStatus;
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
        private UserRole userRole;
        private UserStatus userStatus;

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

        public UserBuilder userRole(UserRole userRole) {
            this.userRole = userRole;
            return this;
        }

        public UserBuilder userStatus(UserStatus userStatus) {
            this.userStatus = userStatus;
            return this;
        }

        public User build() {
            User user = new User();
            user.mobile = this.mobile;
            user.email = this.email;
            user.name = this.name;
            user.userStatus = this.userStatus;
            user.nickName = this.nickName;
            user.password = this.password;
            user.userRole = this.userRole;
            user.ssn = this.ssn;
            user.id = this.id;
            return user;
        }
    }
}
