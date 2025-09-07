package com.example.iam.tenant;

import com.example.iam.common.dto.UserDto;
import com.example.iam.core.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/** Tenant scoped user management controller */
@RestController
@RequestMapping("/tenant/{tenantId}/users")
public class TenantUserController {

    private final UserService userService;

    public TenantUserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@PathVariable("tenantId") UUID tenantId, @RequestBody UserDto dto) {
        return ResponseEntity.ok(userService.createUser(tenantId, dto));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("tenantId") UUID tenantId,
                                              @PathVariable("userId") UUID userId,
                                              @RequestBody UserDto dto) {
        return ResponseEntity.ok(userService.updateUser(tenantId, userId, dto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable("tenantId") UUID tenantId,
                                           @PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(userService.findById(tenantId, userId));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> listUsers(@PathVariable("tenantId") UUID tenantId) {
        return ResponseEntity.ok(userService.listUsers(tenantId));
    }

    @PostMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<String> assignRole(@PathVariable("tenantId") UUID tenantId,
                                             @PathVariable("userId") UUID userId,
                                             @PathVariable("roleId") UUID roleId) {
        // Business logic omitted for brevity
        return ResponseEntity.ok("Role " + roleId + " assigned to user " + userId);
    }

    @DeleteMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<String> removeRole(@PathVariable("tenantId") UUID tenantId,
                                             @PathVariable("userId") UUID userId,
                                             @PathVariable("roleId") UUID roleId) {
        // Business logic omitted for brevity
        return ResponseEntity.ok("Role " + roleId + " removed from user " + userId);
    }
}