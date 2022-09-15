package com.florry.user.member.ui;

import com.florry.user.member.dto.*;
import com.florry.user.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberRestController {
    private final MemberService memberService;

    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.created(
                        URI.create("/api/users/%d".formatted(memberService.signUp(signUpRequest).id())))
                .build();
    }

    @GetMapping
    public ResponseEntity<List<MemberResponse>> findMembers(MemberSearchCondition memberSearchCondition) {
        return ResponseEntity.ok(memberService.findMembersBySearchCondition(memberSearchCondition));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberResponse> updateMember(@PathVariable("id") Long id, @RequestBody MemberRequest memberRequest) {
        return ResponseEntity.ok(memberService.updateMember(id, memberRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> withdrawMember(@PathVariable("id") Long id) {
        memberService.withdrawMember(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> findMember(@PathVariable("id") Long id) {
        return ResponseEntity.ok(memberService.findMember(id));
    }
}
