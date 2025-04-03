package com.fatihkarakus.user_service.services;

import com.fatihkarakus.user_service.dtos.RoleDto;
import com.fatihkarakus.user_service.entities.Permission;
import com.fatihkarakus.user_service.entities.Role;
import com.fatihkarakus.user_service.repositories.PermissionRepository;
import com.fatihkarakus.user_service.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    public Role saveRole(RoleDto roleDto) {
        Role role = new Role();
        role.setName(roleDto.getName());

        Set<Permission> permissions = roleDto.getPermissions().stream()
                .map(permissionDto -> {
                    Permission permission = new Permission();
                    permission.setName(permissionDto.getName());
                    return permission;
                })
                .collect(Collectors.toSet());

        role.setPermissions(permissions);

        return roleRepository.save(role);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role updateRole(Long roleId, Role roleDto) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        role.setName(roleDto.getName());

        Set<Permission> updatedPermissions = roleDto.getPermissions().stream()
                .map(permissionDto -> {
                    Permission permission = new Permission();
                    permission.setName(permissionDto.getName());
                    return permission;
                }).collect(Collectors.toSet());

        role.setPermissions(updatedPermissions);

        return roleRepository.save(role);
    }

    public void deleteRole(Long roleId) {
        roleRepository.deleteById(roleId);
    }

    public Role addPermissionToRole(Long roleId, Long permissionId) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        Permission permission = permissionRepository.findById(permissionId).orElseThrow(() -> new RuntimeException("Permission not found"));

        Set<Permission> permissions = role.getPermissions();
        if (permissions == null) {
            permissions = new HashSet<>();
        }
        permissions.add(permission);
        role.setPermissions(permissions);

        return roleRepository.save(role);
    }

    public Role removePermissionFromRole(Long roleId, Long permissionId) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        Permission permission = permissionRepository.findById(permissionId).orElseThrow(() -> new RuntimeException("Permission not found"));

        role.getPermissions().remove(permission);

        return roleRepository.save(role);
    }
}
