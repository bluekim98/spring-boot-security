package com.example.springbootmembership.domain.user;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoleTest {
    @Test
    void roleTest() {
        //given
        final String GUEST = "ROLE_GUEST";
        final String USER = "ROLE_USER";
        final String ADMIN = "ROLE_ADMIN";
        //when
        String guestRoleKey = Role.GUEST.getKey();
        String userRoleKey = Role.USER.getKey();
        String adminRoleKey = Role.ADMIN.getKey();
        //then
        assertThat(guestRoleKey).isEqualTo(GUEST);
        assertThat(userRoleKey).isEqualTo(USER);
        assertThat(adminRoleKey).isEqualTo(ADMIN);

    }
}