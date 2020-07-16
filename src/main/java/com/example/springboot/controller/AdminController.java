package com.example.springboot.controller;

import com.example.springboot.model.Role;
import com.example.springboot.model.User;
import com.example.springboot.service.RoleService;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("admin/users")
public class AdminController {
    private UserService userService;
    private RoleService roleService;
    private List<Role> allRoles;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
        allRoles = roleService.listRoles();
    }

    @PostMapping
    public String saveUser(@ModelAttribute User user, @RequestParam String[] rolesString) {
        List<Role> rolesFromBD = new ArrayList<>(allRoles);
        List<Role> roleList = Stream.of(rolesString).map(Role::new).collect(Collectors.toList());
        rolesFromBD.retainAll(roleList);
        user.setRoles(new HashSet<>(rolesFromBD));
        if (userService.saveNew(user)) {
            return "success";
        }
        return "failure";
    }

    @PutMapping
    public String updateUser(@ModelAttribute User user, @RequestParam String[] rolesString) {
        List<Role> rolesFromBD = new ArrayList<>(allRoles);
        List<Role> roleList = Stream.of(rolesString).map(Role::new).collect(Collectors.toList());
        rolesFromBD.retainAll(roleList);
        user.setRoles(new HashSet<>(rolesFromBD));
        if (userService.save(user)) {
            return "success";
        }
        return "failure";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "success";
    }

    @GetMapping("/byUsername/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/{id}")
    public User getUserByUsername(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.listRoles();
    }
}