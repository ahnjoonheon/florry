package com.florry;


import com.florry.user.signup.ui.SignUpRestController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserApiApplicationTests {

    @Autowired
    private SignUpRestController signUpRestController;

    @Test
    void contextLoads() {
        Assertions.assertThat(signUpRestController).isNotNull();
    }
}
