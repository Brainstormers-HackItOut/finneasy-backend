package com.finneasy.app.controller;

import com.finneasy.app.jwtutil.JWTUtil;
import com.finneasy.app.model.LoginForm;
import com.finneasy.app.model.LoginResponse;
import com.finneasy.app.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserService userService;
    private JWTUtil jwtUtil;
    private AuthenticationManager authenticationManager;

    public AuthController(UserService userService, JWTUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginForm loginForm){
        //Validate username/password with DB(required in case of Stateless Authentication)
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginForm.getPhoneNumber(), loginForm.getPassword()));
        String token =jwtUtil.generateToken(loginForm.getPhoneNumber());
        return ResponseEntity.ok(new LoginResponse(token,"Token generated successfully!"));
    }

    @GetMapping("/check")
    public String check(){
        return "Ok Tested";
    }
}
