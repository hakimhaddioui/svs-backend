package com.example.demo.Controller;

import com.example.demo.Service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {
/*
    private final LoginService loginService;
    private final UserService userService;

    public LoginController(LoginService loginService, UserService userService) {
        this.loginService = loginService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public String createUser(@RequestBody Users users) {
        if (userService.createUser(users)) {
            return "User created successfully";
        } else {
            return "Error creating user";
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Users loginRequest) {
        System.out.println("Received login request: " + loginRequest.getEmail() + " / " + loginRequest.getPassword());
        Map<String, String> response = new HashMap<>();
        if (loginService.authenticate(loginRequest.getEmail(), loginRequest.getPassword())) {
            response.put("status", "success");
            response.put("message", "Login successful");
            return ResponseEntity.ok(response);
        } else {
            response.put("status", "error");
            response.put("message", "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

*/
}