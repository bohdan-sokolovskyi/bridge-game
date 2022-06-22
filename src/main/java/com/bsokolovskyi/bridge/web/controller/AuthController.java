package com.bsokolovskyi.bridge.web.controller;

import com.bsokolovskyi.bridge.web.request.AuthRequest;
import com.bsokolovskyi.bridge.web.request.SignupRequest;
import com.bsokolovskyi.bridge.web.response.AuthResponse;
import com.bsokolovskyi.bridge.web.response.BaseResponse;
import com.bsokolovskyi.bridge.web.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    public static final Logger log = LogManager.getLogger(AuthController.class);

    private final UserService userService;

    public AuthController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
        AuthResponse response = new AuthResponse();
        String token = "";

        try {
            token = userService.loginUser(authRequest);
        } catch (Exception e) {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setText(e.getMessage());
        }

        response.setAccessToken(token);

        return response;
    }

    @PostMapping(path = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse register(@RequestBody SignupRequest signupRequest) {
        BaseResponse response = new BaseResponse();

        try {
           userService.createNewUser(signupRequest);
        } catch (Exception e) {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setText(e.getMessage());
        }

        return response;
    }
}
