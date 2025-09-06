package com.example.iam.admin.controller;

import com.example.iam.common.dto.UserDto;
import com.example.iam.core.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/** Controller for managing users globally by super admin */
@RestController
@RequestMapping("/admin/users")
public class AdminUserController {

    private final UserService userService;

    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/{tenantId}")
    public ResponseEntity<UserDto> createUser(@PathVariable UUID tenantId, @RequestBody UserDto dto) {
        return ResponseEntity.ok(userService.createUser(tenantId, dto));
    }

    @PutMapping("/{tenantId}/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable UUID tenantId,
                                              @PathVariable UUID userId,
                                              @RequestBody UserDto dto) {
        return ResponseEntity.ok(userService.updateUser(tenantId, userId, dto));
    }

    @DeleteMapping("/{tenantId}/{userId}")
    public ResponseEntity<Void> disableUser(@PathVariable UUID tenantId, @PathVariable UUID userId) {
        userService.disableUser(tenantId, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{tenantId}/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable UUID tenantId, @PathVariable UUID userId) {
        return ResponseEntity.ok(userService.findById(tenantId, userId));
    }

    @GetMapping("/{tenantId}")
    public ResponseEntity<List<UserDto>> listUsers(@PathVariable UUID tenantId) {
        return ResponseEntity.ok(userService.listUsers(tenantId));
    }
}
