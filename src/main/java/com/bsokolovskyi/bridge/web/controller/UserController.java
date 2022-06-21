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
public class UserController {

    public static final Logger log = LogManager.getLogger(UserController.class);

    private final UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody AuthResponse login(@RequestBody AuthRequest authRequest) {
        log.info("POST /login, {}", authRequest);

        AuthResponse response = new AuthResponse();
        String token = "";

        try {
            token = userService.loginUser(authRequest);
        } catch (Exception e) {
            log.info("POST /login, {}", e.getMessage());

            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setText(e.getMessage());
        }

        response.setAccessToken(token);

        log.info("POST /login, {}", response);

        return response;
    }

    @PostMapping(path = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody BaseResponse register(@RequestBody SignupRequest signupRequest) {
        log.info("POST /signup, {}", signupRequest);

        BaseResponse response = new BaseResponse();

        try {
           userService.createNewUser(signupRequest);
        } catch (Exception e) {
            log.info("POST /signup, {}", e.getMessage());

            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setText(e.getMessage());
        }

        log.info("POST /signup, {}", response);

        return response;
    }
}
