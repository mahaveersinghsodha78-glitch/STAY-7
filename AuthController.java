package com.example.hotelapp.controller;

import com.example.hotelapp.model.User;
import com.example.hotelapp.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    private final UserRepository userRepo;
    public AuthController(UserRepository userRepo){ this.userRepo = userRepo; }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User u){
        if(u.getEmail()==null) return ResponseEntity.badRequest().body(Map.of("error","Email required"));
        if(userRepo.findByEmail(u.getEmail()).isPresent()){
            return ResponseEntity.badRequest().body(Map.of("error","Email already used"));
        }
        User saved = userRepo.save(u);
        saved.setPassword(null);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> body){
        String email = body.get("email");
        String password = body.get("password");
        if(email==null || password==null) return ResponseEntity.badRequest().body(Map.of("error","email/password required"));
        return userRepo.findByEmail(email)
                .filter(u -> password.equals(u.getPassword()))
                .map(u -> { u.setPassword(null); return ResponseEntity.ok(u); })
                .orElse(ResponseEntity.status(401).body(Map.of("error","Invalid credentials")));
    }
}
