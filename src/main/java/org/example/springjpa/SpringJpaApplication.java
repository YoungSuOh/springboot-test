package org.example.springjpa;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
@EnableJpaAuditing  // JPA Auditing 기능 활성화
@RequiredArgsConstructor
public class SpringJpaApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringJpaApplication.class, args);
    }

}
