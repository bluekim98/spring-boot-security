package com.example.springbootmembership.domain.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest @Rollback(value = false)
class UserRepositoryTest {

    @Autowired UserRepository userRepository;

    @Test
    void userCRUD() {
        //given
        User user = User.builder()
                        .name("blue")
                        .email("blue@naver.com")
                        .password("123")
                        .role(Role.USER)
                        .build();
        //when
        userRepository.save(user);
        //then
        assertThat(userRepository.count()).isEqualTo(1);

        //given
        String userEmail = user.getEmail();
        //when
        User foundUser = userRepository.findByEmail(userEmail).orElse(
                User.builder().email("another@naver.com").build()
        );

        //then
        assertThat(foundUser.getEmail()).isEqualTo(userEmail);
    }
}