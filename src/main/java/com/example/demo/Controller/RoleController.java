package com.example.demo.Controller;


import com.example.demo.Model.Role;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RoleController {

    @GetMapping("/api/roles")
    public List<Role> getAllRoles() {
        return Arrays.asList(Role.values()); // Récupérer tous les rôles définis dans l'énumération
    }
}
