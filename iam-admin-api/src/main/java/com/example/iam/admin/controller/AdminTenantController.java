package com.example.iam.admin.controller;

import com.example.iam.common.dto.TenantDto;
import com.example.iam.core.service.TenantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/** Controller for managing tenants by system-wide admin */
@RestController
@RequestMapping("/admin/tenants")
public class AdminTenantController {

    private final TenantService tenantService;

    public AdminTenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @PostMapping
    public ResponseEntity<TenantDto> createTenant(@RequestBody TenantDto dto) {
        return ResponseEntity.ok(tenantService.createTenant(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TenantDto> updateTenant(@PathVariable UUID id, @RequestBody TenantDto dto) {
        return ResponseEntity.ok(tenantService.updateTenant(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTenant(@PathVariable UUID id) {
        tenantService.deleteTenant(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<TenantDto>> listTenants() {
        return ResponseEntity.ok(tenantService.listTenants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TenantDto> getTenant(@PathVariable UUID id) {
        return ResponseEntity.ok(tenantService.getTenant(id));
    }
}
