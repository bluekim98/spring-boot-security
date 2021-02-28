package com.example.springbootmembership.service.user;


import com.example.springbootmembership.domain.user.User;
import com.example.springbootmembership.domain.user.UserRepository;
import com.example.springbootmembership.dto.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService{
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public UserDto signUp(UserDto userDto) {
        /*
            1. valid
                - email 중복여부 체크
            2. save
                - password 암호화
                - Entity 생성
                - Entity 저장
            3. after proccess when success
                - 로그인처리
            4. return DTO
                - DTO에서 password 제거
                - user DTO return
         */

        // 1. valid
        if(!isValidToSignUp(userDto)) return userDto;

        // 2. save
        saveUser(userDto);

        // 3. after proccess when success

        // 4. return DTO
        userDto.setPassword(null);
        return userDto;
    }

    private boolean isValidToSignUp(UserDto userDto) {
        boolean isExists = userRepository.existsByEmail(userDto.getEmail());
        if(isExists) {
            userDto.setProcessResultMsg("");
            return false;
        }
        return true;
    }
    private void saveUser(UserDto userDto) {
        final String encodedPassword = passwordEncoder.encode(userDto.getPassword());

        User userEntity = new User();
        userEntity.nomalUserSignUp(userDto.getEmail(), encodedPassword, userDto.getName());

        userRepository.save(userEntity);
    }


    @Override
    public void withdrawal(UserDto userDto) {

    }

    @Override
    public UserDto updateBasicInfo(UserDto userDto) {
        return null;
    }
}
