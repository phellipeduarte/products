package com.products.controllers;

import com.products.dto.AuthenticationDTO;
import com.products.dto.RegisterDTO;
import com.products.models.User;
import com.products.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO authenticationDTO){
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.username(), authenticationDTO.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO registerDTO){
        if(this.userRepository.findByUsername(registerDTO.username()) != null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already in use");
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        User newUser = new User(registerDTO.username(), encryptedPassword, registerDTO.role());
        userRepository.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created");
    }
}