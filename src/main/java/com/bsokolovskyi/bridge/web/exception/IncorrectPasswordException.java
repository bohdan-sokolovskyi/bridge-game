package com.bsokolovskyi.bridge.web.exception;

import com.bsokolovskyi.bridge.web.dto.UserDTO;

public class IncorrectPasswordException extends RuntimeException {

    public IncorrectPasswordException(UserDTO userDTO) {
        super(String.format("incorrect password for user %s", userDTO.getLogin()));
    }
}
