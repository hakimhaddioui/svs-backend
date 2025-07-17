package com.example.demo.Controller;

import com.example.demo.Model.Utilisateurs;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Utilisateurs> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateurs> getUserById(@PathVariable Long id) {
        Optional<Utilisateurs> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Utilisateurs> addUser(@RequestBody Utilisateurs utilisateurs) {
        Utilisateurs newUtilisateurs = userService.addUser(utilisateurs);
        return ResponseEntity.ok(newUtilisateurs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utilisateurs> updateUser(@PathVariable Long id, @RequestBody Utilisateurs utilisateursDetails) {
        Utilisateurs updatedUtilisateurs = userService.updateUser(id, utilisateursDetails);
        return ResponseEntity.ok(updatedUtilisateurs);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/reset-password")
    public ResponseEntity<Utilisateurs> resetPasswordToOrigin(@PathVariable Long id) {
        Utilisateurs utilisateurs = userService.resetPasswordToOrigin(id);
        return ResponseEntity.ok(utilisateurs);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countUsers() {
        long userCount = userService.countUsers();
        return ResponseEntity.ok(userCount);
    }
}
