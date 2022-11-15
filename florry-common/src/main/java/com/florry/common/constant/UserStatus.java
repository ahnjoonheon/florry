package com.florry.common.constant;

public enum UserStatus {
    NORMAL("정상"),
    DORMANT("휴면"),
    WITHDRAW("탈퇴");
    private final String description;
    UserStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
