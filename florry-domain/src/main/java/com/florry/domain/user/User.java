package com.florry.domain.user;

import com.florry.common.constant.UserRole;
import com.florry.common.constant.UserStatus;
import com.florry.domain.common.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Table(
        name = "USER",
        uniqueConstraints = {@UniqueConstraint(columnNames = "NICKNAME", name = "UQ_USER_NICKNAME")}
)
public class User extends BaseTimeEntity {
    @Id
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

    private User(String email, String password, String nickName, UserRole userRole, UserStatus userStatus) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.userRole = userRole;
        this.userStatus = userStatus;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return userRole;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public static final class UserBuilder {
        private String email;
        private String password;
        private String nickName;
        private UserRole userRole;
        private UserStatus userStatus;

        private UserBuilder() {
        }

        public static UserBuilder anUser() {
            return new UserBuilder();
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder nickName(String nickName) {
            this.nickName = nickName;
            return this;
        }

        public UserBuilder role(UserRole userRole) {
            this.userRole = userRole;
            return this;
        }

        public UserBuilder userStatus(UserStatus userStatus) {
            this.userStatus = userStatus;
            return this;
        }

        public User build() {
            return new User(email, password, nickName, userRole, userStatus);
        }
    }
}
