package com.skyspecs.task1.controller;

import com.skyspecs.task1.dto.RoleToUserDto;
import com.skyspecs.task1.entity.Role;
import com.skyspecs.task1.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/all")
    public List<Role> getAllRoles(){
        return roleService.fetchAllRoles();
    }

    @PostMapping("/create")
    public Role createRole(@RequestBody Role role){
        return roleService.createNewRole(role);
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteRole(@PathVariable int id) {
        roleService.deleteRoleById(id);
        return HttpStatus.OK;
    }
}
