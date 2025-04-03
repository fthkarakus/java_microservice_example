package com.fatihkarakus.user_service.controllers;

import com.fatihkarakus.user_service.dtos.PermissionDto;
import com.fatihkarakus.user_service.entities.Permission;
import com.fatihkarakus.user_service.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping
    public Permission createPermission(@RequestBody PermissionDto permissionDto) {
        return permissionService.savePermission(permissionDto);
    }

    @GetMapping
    public List<Permission> getAllPermissions() {
        return permissionService.getAllPermissions();
    }

    @PutMapping("/{permissionId}")
    public Permission updatePermission(@PathVariable Long permissionId, @RequestBody PermissionDto permissionDto) {
        return permissionService.updatePermission(permissionId, permissionDto);
    }

    @DeleteMapping("/{permissionId}")
    public void deletePermission(@PathVariable Long permissionId) {
        permissionService.deletePermission(permissionId);
    }
}