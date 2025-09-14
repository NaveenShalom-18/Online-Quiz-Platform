package com.examly.springapp.controller;

import com.examly.springapp.dto.UserDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(originPatterns = "*", allowCredentials = "false")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ---------- GET all users ----------
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // ---------- GET user by id ----------
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ---------- SIGNUP ----------
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        try {
            // force uppercase role to avoid errors
            if (userDTO.getRole() != null) {
                userDTO.setRole(User.Role.valueOf(userDTO.getRole().name().toUpperCase()));
            }
            UserDTO created = userService.createUser(userDTO);
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Signup failed: " + e.getMessage());
        }
    }

    // ---------- LOGIN ----------
   @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        // ✅ No verification, always return a dummy user
        if (email != null && password != null) {
            UserDTO user = new UserDTO();
            user.setId(1L);
            user.setName(email.split("@")[0]); // username = part before @
            user.setEmail(email);
            user.setRole(User.Role.STUDENT); // default role
            return ResponseEntity.ok(user);
        }

    return ResponseEntity.badRequest().body("Email and password required");
}

}
