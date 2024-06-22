package com.example.L2.S2.Project.controller;

import com.example.L2.S2.Project.dao.request.SignInRequest;
import com.example.L2.S2.Project.dao.request.SignUpRequest;
import com.example.L2.S2.Project.dao.response.JwtAuthenticationResponse;
import com.example.L2.S2.Project.repository.UserRepository;
import com.example.L2.S2.Project.service.AuthenticationService;
import com.example.L2.S2.Project.service.JwtService;
import com.example.L2.S2.Project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/user")
@Component
@RequiredArgsConstructor
public class UserController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signUp(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signUp(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(authenticationService.signIn(request));
    }

    @PostMapping("/adminSignup")
    public ResponseEntity<JwtAuthenticationResponse> adminSignup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.adminSignup(request));
    }

    @PostMapping("/adminSignin")
    public ResponseEntity<JwtAuthenticationResponse> adminSignin(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(authenticationService.signIn(request));
    }
}
