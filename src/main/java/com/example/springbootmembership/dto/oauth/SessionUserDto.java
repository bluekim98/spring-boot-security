package com.example.springbootmembership.dto.oauth;

import com.example.springbootmembership.domain.user.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class SessionUserDto implements Serializable {
    private String name;
    private String email;

    public SessionUserDto(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public SessionUserDto(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
