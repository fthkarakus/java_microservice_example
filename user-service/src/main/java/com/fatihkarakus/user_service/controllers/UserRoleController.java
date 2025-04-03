package com.fatihkarakus.user_service.controllers;

import com.fatihkarakus.user_service.entities.User;
import com.fatihkarakus.user_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-roles")
public class UserRoleController {

    @Autowired
    private UserService userService;

    @PostMapping("/{userId}/roles/{roleId}")
    public User assignRoleToUser(@PathVariable Long userId, @PathVariable Long roleId) {
        return userService.assignRoleToUser(userId, roleId);
    }

    @DeleteMapping("/{userId}/roles/{roleId}")
    public User removeRoleFromUser(@PathVariable Long userId, @PathVariable Long roleId) {
        return userService.removeRoleFromUser(userId, roleId);
    }
}