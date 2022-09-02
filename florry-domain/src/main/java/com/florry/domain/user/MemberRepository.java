package com.florry.domain.user;

import com.florry.common.constant.MemberStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>, JpaSpecificationExecutor<Member> {
    Optional<Member> findByEmailAndPasswordAndMemberStatusIn(String email, String password, Collection<MemberStatus> memberStatuses);
}
