package com.florry;


import com.florry.user.member.ui.MemberRestController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberApiApplicationTests {

    @Autowired
    private MemberRestController memberRestController;

    @Test
    void contextLoads() {
        Assertions.assertThat(memberRestController).isNotNull();
    }
}
