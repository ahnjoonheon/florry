package com.florry.user.member.dto;

import com.florry.common.constant.MemberRole;
import com.florry.common.constant.MemberStatus;
import com.florry.domain.user.QMember;
import com.querydsl.core.BooleanBuilder;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

public record MemberSearchCondition (Long id,
                                     String email,
                                     String name,
                                     String nickName,
                                     String ssn,
                                     String mobile,
                                     MemberRole memberRole,
                                     MemberStatus memberStatus) {
    public BooleanBuilder build() {
        QMember qMember = QMember.member;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (StringUtils.hasText(email)) {
            booleanBuilder.and(qMember.email.contains(this.email));
        }
        if (StringUtils.hasText(name)) {
            booleanBuilder.and(qMember.name.contains(this.name));
        }
        if (StringUtils.hasText(nickName)) {
            booleanBuilder.and(qMember.nickName.contains(this.nickName));
        }
        if (StringUtils.hasText(ssn)) {
            booleanBuilder.and(qMember.ssn.contains(this.ssn));
        }
        if (StringUtils.hasText(mobile)) {
            booleanBuilder.and(qMember.mobile.contains(this.mobile));
        }
        if (!ObjectUtils.isEmpty(memberRole)) {
            booleanBuilder.and(qMember.memberRole.eq(this.memberRole));
        }
        if (!ObjectUtils.isEmpty(memberStatus)) {
            booleanBuilder.and(qMember.memberStatus.eq(this.memberStatus));
        }
        return booleanBuilder;
    }
}
