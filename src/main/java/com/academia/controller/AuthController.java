package com.academia.controller;

import com.academia.dto.AuthRequest;
import com.academia.dto.AuthResponse;
import com.academia.dto.UserRequest;
import com.academia.model.User;
import com.academia.security.JwtTokenProvider;
import com.academia.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
// @CrossOrigin removido - se maneja en SecurityConfig
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );

            String token = jwtTokenProvider.generateToken(authentication);
            User user = userService.findByEmail(authRequest.getEmail()).orElse(null);

            if (user != null) {
                user.setLastLogin(java.time.LocalDateTime.now());
                userService.updateUser(user.getId(), user);
            }

            return ResponseEntity.ok(new AuthResponse(token, authRequest.getEmail(), user != null ? user.getRole() : "", "Login exitoso", user));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(401).body(new AuthResponse(null, null, null, "Credenciales inválidas: " + e.getMessage(), null));
        }
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<?> register(@RequestBody UserRequest userRequest) {
        try {
            Optional<User> existingUser = userService.findByEmail(userRequest.getEmail());
            if (existingUser.isPresent()) {
                User user = existingUser.get();
                if (!user.isActive()) {
                    // Activate the disabled user
                    user.setActive(true);
                    user.setLocked(false);
                    userService.updateUser(user.getId(), user);
                    return ResponseEntity.ok(new AuthResponse(null, user.getEmail(), user.getRole(), "Usuario activado exitosamente", user));
                } else {
                    return ResponseEntity.badRequest().body(new AuthResponse(null, null, null, "El email ya está registrado", null));
                }
            }

            User user = new User();
            user.setEmail(userRequest.getEmail());
            user.setPassword(userRequest.getPassword());
            user.setFullName(userRequest.getFullName());
            user.setPhone(userRequest.getPhone());
            user.setRole(userRequest.getRole() != null ? userRequest.getRole() : "STUDENT");
            user.setDocument(userRequest.getDocument());
            user.setActive(true);
            user.setLocked(false);

            User createdUser = userService.createUser(user);

            return ResponseEntity.ok(new AuthResponse(null, createdUser.getEmail(), createdUser.getRole(), "Registro exitoso", createdUser));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new AuthResponse(null, null, null, "Error en el registro: " + e.getMessage(), null));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok(new AuthResponse(null, null, null, "Logout exitoso", null));
    }

    @PutMapping("/activate/{email}")
    public ResponseEntity<?> activateUser(@PathVariable String email) {
        try {
            User user = userService.findByEmail(email).orElse(null);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            user.setActive(true);
            user.setLocked(false);
            userService.updateUser(user.getId(), user);
            return ResponseEntity.ok(new AuthResponse(null, user.getEmail(), user.getRole(), "Usuario activado", user));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new AuthResponse(null, null, null, "Error activando usuario: " + e.getMessage(), null));
        }
    }
}