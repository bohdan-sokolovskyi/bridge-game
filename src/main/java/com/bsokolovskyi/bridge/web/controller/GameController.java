package com.bsokolovskyi.bridge.web.controller;

import com.bsokolovskyi.bridge.web.config.WebSocketConfig;
import com.bsokolovskyi.bridge.web.request.ConnectGameRequest;
import com.bsokolovskyi.bridge.web.request.CreateGameRequest;
import com.bsokolovskyi.bridge.web.request.GameDataRequest;
import com.bsokolovskyi.bridge.web.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping( "/game")
public class GameController {

    private final GameService gameService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public GameController(@Autowired GameService gameService,
                          @Autowired SimpMessagingTemplate simpMessagingTemplate) {
        this.gameService = gameService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> create(@RequestBody CreateGameRequest request) {
        return ResponseEntity.ok(Collections.singletonMap("gameId", gameService.createGame(request)));
    }

    @PostMapping("/connect")
    public ResponseEntity<Object> connect(@RequestBody ConnectGameRequest request) {
        gameService.connectToGame(request);
        return ResponseEntity.ok().build();
    }

    @RequestMapping("/play")
    public void play(@RequestBody GameDataRequest request) {
        simpMessagingTemplate.convertAndSend(
                String.format("%s/progress/%s", WebSocketConfig.BROKER_PATH, request.getGameId()),
                (Object) null);
    }
}
