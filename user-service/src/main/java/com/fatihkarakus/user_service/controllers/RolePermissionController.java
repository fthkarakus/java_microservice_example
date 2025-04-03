package com.fatihkarakus.user_service.controllers;

import com.fatihkarakus.user_service.entities.Role;
import com.fatihkarakus.user_service.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role-permissions")
public class RolePermissionController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/{roleId}/permissions/{permissionId}")
    public Role addPermissionToRole(@PathVariable Long roleId, @PathVariable Long permissionId) {
        return roleService.addPermissionToRole(roleId, permissionId);
    }

    @DeleteMapping("/{roleId}/permissions/{permissionId}")
    public Role removePermissionFromRole(@PathVariable Long roleId, @PathVariable Long permissionId) {
        return roleService.removePermissionFromRole(roleId, permissionId);
    }
}