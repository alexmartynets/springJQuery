package com.example.springboot.service;

import com.example.springboot.model.Role;
import com.example.springboot.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getRolesByRole(String role) {
        return roleRepository.getRolesByRole(role);
    }

    @Override
    public List<Role> listRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Set<String> findAllRole() {
        return roleRepository.findAll().stream().map(Role::getAuthority).collect(Collectors.toSet());
    }
}