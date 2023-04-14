package com.jake.loan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JakeloanApplication {

    public static void main(String[] args) {
        SpringApplication.run(JakeloanApplication.class, args);
    }

}
