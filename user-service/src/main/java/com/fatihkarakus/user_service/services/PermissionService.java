package com.fatihkarakus.user_service.services;

import com.fatihkarakus.user_service.dtos.PermissionDto;
import com.fatihkarakus.user_service.entities.Permission;
import com.fatihkarakus.user_service.entities.User;
import com.fatihkarakus.user_service.repositories.PermissionRepository;
import com.fatihkarakus.user_service.repositories.RoleRepository;
import com.fatihkarakus.user_service.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Transactional
    public Permission savePermission(PermissionDto input) {
        Permission permission = new Permission();
        permission.setName(input.getName());

        return permissionRepository.save(permission);
    }

    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    @Transactional
    public Permission updatePermission(Long id, PermissionDto permissionDto) {
        Optional<Permission> existingPermissionOpt = permissionRepository.findById(id);
        if (existingPermissionOpt.isPresent()) {
            Permission existingPermission = existingPermissionOpt.get();
            existingPermission.setName(permissionDto.getName());
            return permissionRepository.save(existingPermission);
        } else {
            throw new NotFoundException("Permission not found with id: " + id);
        }
    }

    public void deletePermission(Long permissionId) {
        permissionRepository.deleteById(permissionId);
    }

    public boolean hasPermission(String email, String permissionName) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return false;
        }

        return user.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .anyMatch(permission -> permission.getName().equals(permissionName));
    }
}
