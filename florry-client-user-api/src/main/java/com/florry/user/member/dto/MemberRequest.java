package com.florry.user.member.dto;

import com.florry.common.constant.MemberRole;
import com.florry.common.constant.MemberStatus;

public record MemberRequest(String email,
                            String password,
                            String name,
                            String nickName,
                            String ssn,
                            String mobile,
                            MemberRole memberRole,
                            MemberStatus memberStatus) {
}
