package com.bsokolovskyi.bridge.web.controller;

import com.bsokolovskyi.bridge.web.request.RefreshAccessTokenRequest;
import com.bsokolovskyi.bridge.web.response.RefreshAccessTokenResponse;
import com.bsokolovskyi.bridge.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/access_token")
public class AccessTokenController {

    private final UserService userService;

    public AccessTokenController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/refresh")
    public @ResponseBody RefreshAccessTokenResponse refreshToken(@RequestBody RefreshAccessTokenRequest refreshAccessTokenRequest) {
        RefreshAccessTokenResponse refreshAccessTokenResponse = new RefreshAccessTokenResponse();
        String newToken = "";

        try {
            newToken = userService.refreshAccessToken(refreshAccessTokenRequest);
        } catch (Exception e) {
            refreshAccessTokenResponse.setStatus(HttpStatus.BAD_REQUEST);
            refreshAccessTokenResponse.setText(e.getMessage());
        }

        refreshAccessTokenResponse.setAccessToken(newToken);

        return refreshAccessTokenResponse;
    }
}
