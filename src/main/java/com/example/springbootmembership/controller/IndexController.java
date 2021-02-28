package com.example.springbootmembership.controller;

import com.example.springbootmembership.dto.oauth.SessionUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/")
public class IndexController {

    private final HttpSession httpSession;

    @Autowired
    public IndexController(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @GetMapping(value = "")
    public String indexForm(Model model) {
        SessionUserDto user = (SessionUserDto) httpSession.getAttribute("user");
        model.addAttribute("user", user);
        return "index";
    }
}
