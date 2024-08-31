package com.example.AuthenticationService.controller;


import com.example.AuthenticationService.config.CustomUserDetails;
import com.example.AuthenticationService.dto.AuthRequest;
import com.example.AuthenticationService.model.UserCredential;
import com.example.AuthenticationService.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String addNewUser(@RequestBody UserCredential user) {


        user = service.saveUser(user);
        return service.generateToken(user.getUsername(),user.getId(),user.getRole().name());
    }

    @PostMapping("/login")
    public String getToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            CustomUserDetails userCredential = (CustomUserDetails) authenticate.getPrincipal();
            return service.generateToken(authRequest.getUsername(),userCredential.getId(),userCredential.getRole());
        } else {
            throw new RuntimeException("invalid access");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        service.validateToken(token);
        return "Token is valid";
    }

    @GetMapping("/validateId")
    public Boolean validateId(@RequestParam("id") String id) {
        return service.validateId(id);
    }
}