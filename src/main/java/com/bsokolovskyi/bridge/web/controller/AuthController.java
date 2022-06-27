package com.bsokolovskyi.bridge.web.controller;

import com.bsokolovskyi.bridge.web.request.AuthRequest;
import com.bsokolovskyi.bridge.web.request.SignupRequest;
import com.bsokolovskyi.bridge.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping( "/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @PostMapping( "/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(Collections.singletonMap("accessToken", userService.loginUser(authRequest)));
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> register(@RequestBody SignupRequest signupRequest) {
        userService.createNewUser(signupRequest);
        return ResponseEntity.ok().build();
    }
}
