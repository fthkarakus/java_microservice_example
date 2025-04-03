package com.fatihkarakus.user_service.controllers;

import com.fatihkarakus.user_service.dtos.RoleDto;
import com.fatihkarakus.user_service.entities.Role;
import com.fatihkarakus.user_service.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public Role createRole(@RequestBody RoleDto roleDto) {
        return roleService.saveRole(roleDto);
    }

    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PutMapping("/{roleId}")
    public Role updateRole(@PathVariable Long roleId, @RequestBody Role role) {
        return roleService.updateRole(roleId, role);
    }

    @DeleteMapping("/{roleId}")
    public void deleteRole(@PathVariable Long roleId) {
        roleService.deleteRole(roleId);
    }
}
