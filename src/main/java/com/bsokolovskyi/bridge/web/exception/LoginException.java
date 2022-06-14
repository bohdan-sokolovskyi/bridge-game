package com.bsokolovskyi.bridge.web.exception;

import com.bsokolovskyi.bridge.web.dto.UserDTO;

public class LoginException extends RuntimeException {

    public LoginException(UserDTO userDTO) {
        super(String.format("user %s not found", userDTO.getLogin()));
    }
}
