package com.skyspecs.task1.service;

import com.skyspecs.task1.entity.Role;
import com.skyspecs.task1.entity.User;
import com.skyspecs.task1.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> fetchAllRoles(){
        return roleRepository.findAll();
    }

    public Role createNewRole(Role role){
        return roleRepository.save(role);
    }

    public void deleteRoleById(int id){
        roleRepository.deleteById(id);
    }

    public Role fetchRoleById(int id){
        Optional<Role> optional = roleRepository.findById(id);
        return optional.get();
    }
    public Role updateRole(int id, Role role){
        role.setId(id);
       return roleRepository.save(role);
    }

    public Role findByRole(String role){
        Role role1 = roleRepository.findByRole(role);
        return role1;
    }


}
