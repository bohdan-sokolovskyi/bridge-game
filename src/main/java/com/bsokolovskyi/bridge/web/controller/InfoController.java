package com.bsokolovskyi.bridge.web.controller;

import com.bsokolovskyi.bridge.web.dto.GameDTO;
import com.bsokolovskyi.bridge.web.dto.UserDTO;
import com.bsokolovskyi.bridge.web.service.GameService;
import com.bsokolovskyi.bridge.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/info")
public class InfoController {

    private final UserService userService;
    private final GameService gameService;

    public InfoController(@Autowired UserService userService,
                          @Autowired GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @GetMapping("/games")
    public ResponseEntity<Map<String, GameDTO>> games() {
        return ResponseEntity.ok(gameService.getAllGames());
    }

    @GetMapping("/users")
    public ResponseEntity<Map<String, UserDTO>> users() {
        return ResponseEntity.ok(userService.getAllUsersInfo());
    }
}
