package com.example.springbootmembership.dto.user;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Data
public class UserDto {
    private String email;
    private String password;
    private String name;
    private String processResultMsg;

    @Builder
    public UserDto(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
