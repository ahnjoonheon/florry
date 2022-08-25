package com.florry.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EntityScan(basePackages = "com.florry.domain")
//@EnableJpaRepositories(basePackages = "com.florry.domain")
@EnableJpaAuditing
@SpringBootApplication
public class UserApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApiApplication.class, args);
    }
}
