package com.example.springbootmembership.controller;


import com.example.springbootmembership.dto.user.UserDto;
import com.example.springbootmembership.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired UserService userService;

    @GetMapping(value = "login")
    public String loginForm() {
        return "login";
    }
    @GetMapping(value = "signUpPage")
    public String signUpForm() {
        return "signUp";
    }
    @PostMapping(value = "signUpProcess")
    public String signUpProcess(UserDto userDto, Model model){
        UserDto afterUserDto = userService.signUp(userDto);
        model.addAttribute("user", afterUserDto);
        return "index";
    }
}
