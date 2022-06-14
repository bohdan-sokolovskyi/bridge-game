package com.bsokolovskyi.bridge.web.exception;

import com.bsokolovskyi.bridge.web.dto.UserDTO;

public class RegistrationException extends RuntimeException {

    public RegistrationException(UserDTO userDTO) {
        super(String.format("user %s already exist", userDTO.getLogin()));
    }

}
