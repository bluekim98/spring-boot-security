package com.example.springbootmembership.service.user;

import com.example.springbootmembership.domain.user.User;
import com.example.springbootmembership.domain.user.UserRepository;
import com.example.springbootmembership.dto.user.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@Rollback(value = false)
class UserServiceImplTest {
    private final String EMAIL = "blue@naver.com";
    private final String PASSWORD = "123";
    private final String NAME = "blue";

    @Autowired private UserService userService;
    @Autowired private UserRepository userRepository;

    @Test
    void signUpTest() {
        //before
        boolean beforeIsExistsByEmail = userRepository.existsByEmail(EMAIL);
        assertThat(beforeIsExistsByEmail).isEqualTo(false);

        //after
        //given
        UserDto userDto = UserDto.builder()
                            .email(EMAIL)
                            .password(PASSWORD)
                            .name(NAME)
                            .build();
        //when
        UserDto afterUserDto = userService.signUp(userDto);
        //then
        boolean afterIsExistsByEmail = userRepository.existsByEmail(EMAIL);
        assertThat(afterIsExistsByEmail).isEqualTo(true);

        String afterPassword = afterUserDto.getPassword();
        assertThat(afterPassword).isEqualTo(null);

        User afterUserEntity = userRepository.findByEmail(EMAIL)
                                            .orElseThrow(() -> new UsernameNotFoundException(EMAIL));
        assertThat(afterUserEntity.getEmail()).isEqualTo(EMAIL);
        assertThat(afterUserEntity.getPassword()).isNotEqualTo(PASSWORD);
        assertThat(afterUserEntity.getName()).isEqualTo(NAME);
    }
}