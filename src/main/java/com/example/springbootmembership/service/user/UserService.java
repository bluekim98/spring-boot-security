package com.example.springbootmembership.service.user;

import com.example.springbootmembership.dto.user.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDto signUp(UserDto userDto);
    void withdrawal(UserDto userDto);
    UserDto updateBasicInfo(UserDto userDto);
}
