package com.bsokolovskyi.bridge.web.controller;

import com.bsokolovskyi.bridge.web.request.RefreshAccessTokenRequest;
import com.bsokolovskyi.bridge.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping( "/access_token")
public class AccessTokenController {

    private final UserService userService;

    public AccessTokenController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/refresh")
    public ResponseEntity<Map<String, String>> refreshToken(@RequestBody RefreshAccessTokenRequest refreshAccessTokenRequest) {
        return ResponseEntity.ok(Collections.singletonMap(
                "accessToken",
                userService.refreshAccessToken(refreshAccessTokenRequest))
        );
    }
}
