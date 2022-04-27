package com.skyspecs.task1.service;

import com.skyspecs.task1.entity.Role;
import com.skyspecs.task1.repository.RoleRepository;

import java.util.List;

public interface RoleService {
    List<Role> fetchAllRoles();
    Role createNewRole(Role role);
    void deleteRoleById(int id);
    public Role findByRole(String role);


}
