package com.fatihkarakus.user_service.config;

import com.fatihkarakus.user_service.entities.Permission;
import com.fatihkarakus.user_service.entities.Role;
import com.fatihkarakus.user_service.entities.User;
import com.fatihkarakus.user_service.repositories.PermissionRepository;
import com.fatihkarakus.user_service.repositories.RoleRepository;
import com.fatihkarakus.user_service.repositories.UserRepository;
import com.fatihkarakus.user_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class DataInitializer {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner loadData() {
        return args -> {
            // Default izinleri oluştur
            Permission addPermission = permissionRepository.findByName("ADD_PERMISSION")
                    .orElseGet(() -> permissionRepository.save(new Permission("ADD_PERMISSION")));
            Permission deletePermission = permissionRepository.findByName("DELETE_PERMISSION")
                    .orElseGet(() -> permissionRepository.save(new Permission("DELETE_PERMISSION")));
            Permission viewProducts = permissionRepository.findByName("VIEW_PRODUCTS")
                    .orElseGet(() -> permissionRepository.save(new Permission("VIEW_PRODUCTS")));

            // Admin rolünü oluştur
            Role adminRole = roleRepository.findByName("ADMIN").orElse(null);
            if (adminRole == null) {
                Set<Permission> permissions = new HashSet<>();
                permissions.add(addPermission);
                permissions.add(deletePermission);

                adminRole = new Role();
                adminRole.setName("ADMIN");
                adminRole.setPermissions(permissions);

                roleRepository.save(adminRole);
            }

            // Eğer admin kullanıcısı yoksa, ekle
            if (!userRepository.existsByEmail("admin@admin.com")) {
                User user = new User();
                user.setEmail("admin@admin.com");
                user.setPassword(passwordEncoder.encode("admin"));
                user.setRoles(Set.of(adminRole));
                user.setFullName("Admin");
                user.setUsername("admin@admin.com");

                userService.saveUser(user);
                System.out.println("Admin user created with email: admin@admin.com");
            }
        };
    }
}