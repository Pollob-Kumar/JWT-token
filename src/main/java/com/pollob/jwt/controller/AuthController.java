package com.pollob.jwt.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.pollob.jwt.security.JwtUtil;

@RestController
public class AuthController {

    private JwtUtil jwtUtil = new JwtUtil();

    // Memory তে users রাখবো (DB নেই এখন)
    private Map<String, String> users = new HashMap<>();
    //                email → password


    // ───────────────────────────────
    // API 1: Register
    // POST /register
    // Body: { "email": "rakib@gmail.com", "password": "123" }
    // ───────────────────────────────
    @PostMapping("/register")
    public String register(@RequestBody Map<String, String> body) {

        String email = body.get("email");
        String password = body.get("password");

        users.put(email, password);  // memory তে save করলাম

        return "Registration done! Email: " + email;
    }


    // ───────────────────────────────
    // API 2: Login → Token পাবে
    // POST /login
    // Body: { "email": "rakib@gmail.com", "password": "123" }
    // ───────────────────────────────
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> body) {

        String email = body.get("email");
        String password = body.get("password");

        // User আছে কিনা দেখো
        if (!users.containsKey(email)) {
            return "User not found!";
        }

        // Password ঠিক আছে কিনা দেখো
        if (!users.get(email).equals(password)) {
            return "Wrong password!";
        }

        // সব ঠিক আছে → Token বানাও
        String token = jwtUtil.createToken(email);
        return "Your token: " + token;
    }


    // ───────────────────────────────
    // API 3: Profile → Token দিলে দেখাবে
    // GET /profile
    // Header: token = eyJhbGci...
    // ───────────────────────────────
    @GetMapping("/profile")
    public String profile(@RequestHeader("token") String token) {

        // Token থেকে email বের করো
        String email = jwtUtil.getEmail(token);

        return "Hello " + email + "! তুমি সফলভাবে token দিয়ে access করেছো!";
    }
}
//```

//---
//
//## Postman দিয়ে Test করো
//
//**Step 1 — Register:**
//```
//POST http://localhost:8080/register
//Body (JSON): 
//{
//  "email": "rakib@gmail.com",
//  "password": "123"
//}
//
//Response: "Registration done! Email: rakib@gmail.com"
//```
//
//**Step 2 — Login (Token পাবে):**
//```
//POST http://localhost:8080/login
//Body (JSON):
//{
//  "email": "rakib@gmail.com", 
//  "password": "123"
//}
//
//Response: "Your token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIi..."
//           ↑ এই লম্বা string টাই তোমার JWT Token!
//```
//
//**Step 3 — Profile (Token use করো):**
//```
//GET http://localhost:8080/profile
//Header: 
//  Key:   token
//  Value: eyJhbGciOiJIUzI1NiJ9.eyJzdWIi...  ← login এ যেটা পেয়েছিলে
//
//Response: "Hello rakib@gmail.com! তুমি সফলভাবে token দিয়ে access করেছো!"
//```
//
//**Step 4 — ভুল Token দাও:**
//```
//GET http://localhost:8080/profile
//Header:
//  Key:   token
//  Value: abcdefg_fake_token
//
//Response: Error! Token invalid ❌
//```
//
//---
//
//## মাথায় রাখো
//```
//JwtUtil   → শুধু token বানায় আর পড়ে
//Controller → API handle করে
//User       → শুধু email আর password রাখে
//
//DB নেই → Memory তে রাখছি (simple রাখতে)
//Security নেই → সরাসরি token header এ পাঠাচ্ছি