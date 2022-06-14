package com.bsokolovskyi.bridge.web.controllers;

import com.bsokolovskyi.bridge.web.dto.UserDTO;
import com.bsokolovskyi.bridge.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/login", produces = "application/json; charset=UTF-8")
    public void login(@RequestBody UserDTO userDTO) {
        userService.login(userDTO);
    }

    @PostMapping(path = "/register", produces = "application/json; charset=UTF-8")
    public void register(@RequestBody UserDTO userDTO) {
        userService.register(userDTO);
    }

    @PostMapping(path = "/logout", produces = "application/json; charset=UTF-8")
    public void logout() {
        //TODO: implement me
    }
}
