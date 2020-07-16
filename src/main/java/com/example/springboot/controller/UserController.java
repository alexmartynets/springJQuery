package com.example.springboot.controller;

import com.example.springboot.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController {

    @GetMapping
    public String user(ModelMap model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user);
        return "userPage";
    }

    @GetMapping("/hasAdminRole")
    @ResponseBody
    public Boolean hasAdminRole(Authentication authentication) {
        return authentication.getAuthorities().stream().anyMatch(role -> "ADMIN".equals(role.getAuthority()));
    }

    @GetMapping("/getUsername")
    @ResponseBody
    public String getUsername(Authentication authentication) {
        return ((UserDetails) authentication.getPrincipal()).getUsername();
    }

    @GetMapping("/getUserRoles")
    @ResponseBody
    public String getUserRoles(Authentication authentication) {
        return ((UserDetails) authentication.getPrincipal()).getAuthorities().toString();
    }
}