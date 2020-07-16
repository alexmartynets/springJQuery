package com.example.springboot.controller;

import com.example.springboot.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @GetMapping("/")
    public String getMainPage(Authentication authentication){
        if (authentication != null){
            User user = (User) authentication.getPrincipal();
            if (user.getAuthorities().stream().anyMatch(role -> "ADMIN".equals(role.getAuthority()))){
                return "redirect:/admin/usersPage";
            }
            return "redirect:/user";
        }
        return "redirect:/login";
    }

    @GetMapping("/admin/usersPage")
    public String getAdminPanelPage() {
        return "admin";
    }

    @GetMapping("/login")
    public String getLoginPage(Authentication authentication, HttpServletRequest request) {
        if (authentication != null) {
            return "redirect:/";
        }
        if (request.getParameterMap().containsKey("error")) {
            return "error";
        } else if (request.getParameterMap().containsKey("logout")) {
            return "logout";
        }
        return "loginPage";
    }
}