package com.florry.common.constant;

public enum UserRole {
    USER("일반 사용자"),
    OPERATOR("운영자"),
    MASTER("마스터");

    private final String description;

    UserRole(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
