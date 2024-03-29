package com.finneasy.app.controller;

import com.finneasy.app.model.UserModel;
import com.finneasy.app.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody UserModel userModel){
        return ResponseEntity.ok(userService.registerUser(userModel));
    }

    @GetMapping("/{phoneNumber}")
    public ResponseEntity<?> getUser(@PathVariable String phoneNumber){
        return ResponseEntity.ok(userService.getUserByPhoneNumber(phoneNumber));
    }
}
