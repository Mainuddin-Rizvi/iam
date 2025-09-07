package com.example.iam.tenant;

import com.example.iam.auth.jwt.JwtUtil;
import com.example.iam.common.dto.LoginRequest;
import com.example.iam.common.dto.TokenResponse;
import com.example.iam.domain.entity.User;
import com.example.iam.domain.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager,
                                    JwtUtil jwtUtil,
                                    UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Get user details from database
            User user = userRepository.findAll().stream()
                    .filter(u -> u.getUsername().equals(request.getUsername()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Generate JWT token
            Map<String, Object> claims = new HashMap<>();
            claims.put("tenantId", user.getTenant().getId().toString());
            claims.put("userId", user.getId().toString());
            claims.put("username", user.getUsername());

            String token = jwtUtil.generateToken(user.getUsername(), claims);

            TokenResponse response = new TokenResponse();
            response.setAccessToken(token);
            response.setTokenType("Bearer");
            response.setExpiresAt(Instant.now().plusSeconds(3600));

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<Map<String, Object>> validateToken(@RequestHeader("Authorization") String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                var claims = jwtUtil.parseToken(token);
                Map<String, Object> response = new HashMap<>();
                response.put("valid", true);
                response.put("username", claims.getSubject());
                response.put("expiresAt", claims.getExpiration());
                return ResponseEntity.ok(response);
            } catch (Exception e) {
                Map<String, Object> response = new HashMap<>();
                response.put("valid", false);
                response.put("error", e.getMessage());
                return ResponseEntity.status(401).body(response);
            }
        }
        return ResponseEntity.badRequest().build();
    }
}